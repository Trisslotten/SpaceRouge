package com.trisse.spacerouge;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.gui.*;
import com.trisse.spacerouge.level.Map;
import com.trisse.spacerouge.util.*;

public class Game implements Runnable {

	public Input input = new Input();
	public Sprites sprites;

	public ActorTypePool actorPool;
	public ItemTypePool itemPool;
	public TileTypePool tilePool;

	public Map map;
	public ArrayList<Actor> actors;

	ArrayList<Actor> deadList = new ArrayList<Actor>();

	Graphics graphics;

	// current actor index
	int cai = 0;

	private int xoffset;
	private int yoffset;

	private DirectedAction queuedAction = null;

	protected void init() {
		sprites = new Sprites();
		screen = new Screen(sprites);

		actorPool = new ActorTypePool(sprites);
		itemPool = new ItemTypePool(sprites);
		tilePool = new TileTypePool(sprites);

		map = new Map(this);
		
		actors = map.getActors();

		graphics = new Graphics(this);
	}

	// injects the input into the player
	protected boolean handleInput() {
		Actor player = actors.get(cai);
		Keyboard.next();
		int key = Keyboard.getEventKey();
		if (Input.controlPressed(key)) {
			if (queuedAction != null) {
				switch (key) {
				case Keyboard.KEY_UP:
					nextActionDirection(Direction.UP);
					break;
				case Keyboard.KEY_DOWN:
					nextActionDirection(Direction.DOWN);
					break;
				case Keyboard.KEY_LEFT:
					nextActionDirection(Direction.LEFT);
					break;
				case Keyboard.KEY_RIGHT:
					nextActionDirection(Direction.RIGHT);
					break;
				case Keyboard.KEY_PERIOD:
					nextActionDirection(Direction.NONE);
					break;
				}
			} else {
				switch (key) {
				case Keyboard.KEY_UP:
					walk(Direction.UP);
					break;
				case Keyboard.KEY_DOWN:
					walk(Direction.DOWN);
					break;
				case Keyboard.KEY_LEFT:
					walk(Direction.LEFT);
					break;
				case Keyboard.KEY_RIGHT:
					walk(Direction.RIGHT);
					break;
				case Keyboard.KEY_PERIOD:
					walk(Direction.NONE);
					break;
				case Keyboard.KEY_D:
					player.setNextAction(new DropAction(player, map));
					break;
				case Keyboard.KEY_C:
					queuedAction = new CloseDoorAction(player, map);
					return false;
				case Keyboard.KEY_O:
					queuedAction = new OpenDoorAction(player, map);
					return false;
				case Keyboard.KEY_G:
					queuedAction = new GrabAction(player, map);
					return false;
				case Keyboard.KEY_U:
					player.setNextAction(new UseItemAction(player, map));
				}
			}
			return true;
		} else {
			return false;
		}
	}

	// sets a new directed action and clears the old one
	private void nextActionDirection(Direction dir) {
		queuedAction.setDirection(dir);
		actors.get(cai).setNextAction(queuedAction);
		queuedAction = null;
	}

	// sets a walk-action to the current actor
	protected void walk(Direction dir) {
		actors.get(cai).walk(dir);
	}

	/**
	 * updates all actors that have an action
	 */
	protected void update() {
		double startTime = getTime();
		while (getTime() - startTime < 1.0 / FPS) {
			Actor actor = actors.get(cai);
			actor.think();
			if (actor.isPlayer) {
				if (!handleInput()) {
					tickCounter = 0;
				}
				setOffsetToActor(actor);
			}
			Action action = actor.getAction();
			if (action == null) {
				return;
			}
			if (actor.isPlayer) {
				double tickInterval = tickCounter > slowToFastTickCount ? tickIntervalFast : this.tickInterval;
				if (tickInterval <= getTime() - lastTick || tickCounter <= 0) {
					tickCounter++;
					while (true) {
						ActionResult result = action.perform(this);
						lastTick = getTime();
						if (!result.success()) {
							graphics.addNotification(result.getMessage());
							return;
						}
						if (result.alternative() == null) {
							
							//if an action was performed then tick the actor 
							actor.tick();
							graphics.addNotification(result.getMessage());
							break;
						}
						action = result.alternative();
					}
					setOffsetToActor(actor);
				} else {
					return;
				}
			} else {
				while (true) {
					ActionResult result = action.perform(this);
					if (result.alternative() == null)
						break;
					action = result.alternative();
					if (!result.success()) {
						return;
					}
				}

			}
			for (Actor a : actors) {
				if (a.isDead())
					deadList.add(a);
			}
			actors.removeAll(deadList);
			for (Actor a : deadList) {
				map.addNewItem(a.getCorpse(itemPool), a.x(), a.y());
			}
			deadList.clear();
			cai = (cai + 1) % actors.size();
		}
	}

	// returns if there is no player
	public boolean noPlayer() {
		for (Actor a : actors) {
			if (a.isPlayer)
				return false;
		}
		return true;
	}

	public void setOffsetToActor(Actor actor) {
		xoffset = actor.x() - Screen.tileWidth / 2;// + Screen.guiWidth;
		yoffset = actor.y() - Screen.tileHeight / 2;
	}

	// draws the sprites that are in the screen instans and clears it
	protected void render() {
		map.render(screen, xoffset, yoffset);
		graphics.render(screen);
		screen.render();
		screen.clear();
	}

	public void saveGame() {

	}

	private Screen screen;

	private static int FPS = 60;
	public static Random rand = new Random();

	public int width = Screen.tileSize * Screen.tileWidth;
	public int height = Screen.tileSize * Screen.tileHeight;

	private boolean running;

	private Thread thread;

	private final double tickInterval = 0.2;
	private final double tickIntervalFast = 0.05;
	private final int slowToFastTickCount = 1;
	private int tickCounter = 0;
	private double lastTick = Game.getTime();

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		game.start();
		game.thread.join();
	}

	// Main game loop
	public void run() {
		glinit();
		double timer = getTime();
		int frames = 0;

		init();
		while (running) {
			if (Display.isCloseRequested()) {
				running = false;
				break;
			}
			update();

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
			Display.update();
			frames++;
			Display.sync(FPS);
			if (getTime() > timer + 1) {
				Display.setTitle("FPS: " + frames);

				timer += 1;
				frames = 0;
			}
		}
		System.exit(0);

	}

	// initializes OpenGL stuff
	public void glinit() {
		try {
			DisplayMode displayMode = new DisplayMode(width, height);
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			boolean validMode = false;
			for (int i = 0; i < modes.length; i++) {
				if (modes[i].getWidth() == width && modes[i].getHeight() == height && modes[i].isFullscreenCapable()) {
					displayMode = modes[i];
					validMode = true;
				}
			}
			if (!validMode) {
				displayMode = new DisplayMode(width, height);
			}
			Display.setDisplayMode(displayMode);
			Display.setResizable(false);
			Display.setFullscreen(false);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		@SuppressWarnings("unused")
		String version = glGetString(GL_VERSION);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// enable alpha blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public static double getTime() {
		return ((double) System.nanoTime()) / 1000000000.0;
	}

}
