package com.trisse.spacerouge;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.actor.types.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.level.*;
import com.trisse.spacerouge.util.*;

public class Game implements Runnable {

	public Input input = new Input();
	public Sprites sprites;

	public ActorTypePool actorPool;
	public ItemTypePool itemPool;
	public TileTypePool tilePool;

	Area area;
	ArrayList<Actor> actors = new ArrayList<Actor>();

	Items items = new Items();

	// current actor index
	int cai = 0;
	private int xoffset;
	private int yoffset;

	protected void init() {
		sprites = new Sprites();
		screen = new Screen(sprites);

		actorPool = new ActorTypePool(sprites);
		itemPool = new ItemTypePool(sprites);
		tilePool = new TileTypePool(sprites);

		area = new Area(tilePool);

		actors.add(new Player(-5, -5, area));
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				actors.add(new Actor(i * 3 + 20, j * 3, area));
			}
		}
		for (Actor a : actors) {
			a.init();
			if (a.isPlayer) {

				xoffset = a.x() - Screen.tileWidth / 2;
				yoffset = a.y() - Screen.tileHeight / 2;

			}
		}
		// gameState = new MainMenuState(sprites, entityList);
		// Keyboard.enableRepeatEvents(false);
	}

	protected boolean handleInput() {
		Keyboard.next();
		int key = Keyboard.getEventKey();
		if (Input.controlPressed(key)) {
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
			}
			return true;
		} else {
			return false;
		}
	}

	protected void walk(Direction dir) {
		Actor actor = actors.get(cai);
		actor.setNextAction(new WalkAction(actor, area, dir));
	}

	protected void update() {
		do {
			Actor actor = actors.get(cai);
			Action action = actor.getAction();
			if (actor.isPlayer) {
				if (!Input.controlPressed()) {
					tickCounter = 0;
				}
				if (!handleInput() && action != Action.waitForNextAction) {
					actor.setNextAction(Action.waitForInput);
				}
			}
			if (action == Action.waitForNextAction) {
				System.out.println("update next 1 | " + actor.isPlayer + " | " + getTime());
				updateNextActor();
			} else if (action == Action.waitForInput) {
				break;
			} else {
				if (actor.isPlayer) {
					double tickInterval = tickCounter > slowToFastTickCount ? tickIntervalFast : this.tickInterval;
					if (tickInterval <= getTime() - lastTick || tickCounter <= 0) {
						tickCounter++;
						boolean success = action.perform();
						lastTick = getTime();
						xoffset = actor.x() - Screen.tileWidth / 2;
						yoffset = actor.y() - Screen.tileHeight / 2;
						if (success) {
							System.out.println("update next 2 | " + actor.isPlayer + " | " + getTime());
							updateNextActor();
						} else {
							break;
						}
					}
				} else {
					if (action.perform()) {
						System.out.println("update next 3 | " + actor.isPlayer + " | " + getTime());
						updateNextActor();
					} else {
						break;
					}
				}

			}
		} while (true);
	}

	private void updateNextActor() {
		cai = (cai + 1) % actors.size();
		Actor actor = actors.get(cai);
		actor.update();

		actor.think();
	}

	protected void render() {
		area.render(screen, xoffset, yoffset);
		items.render(screen, xoffset, yoffset);
		for (Actor actor : actors)
			actor.render(screen, xoffset, yoffset);
		screen.render();
		screen.clear();
	}

	public void saveGame() {

	}

	private Screen screen;

	private static int FPS = 60;

	public int width = Screen.tileSize * Screen.tileWidth;
	public int height = Screen.tileSize * Screen.tileHeight;

	private boolean running;

	private Thread thread;

	public final double tickInterval = 1;
	public final double tickIntervalFast = 0.05;
	public final int slowToFastTickCount = 1;
	public int tickCounter = 0;
	public boolean canTick = false;
	public double lastTick = Game.getTime();
	private boolean controlPressed;

	public void canTick() {
		canTick = true;
	}

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

	public static UnsupportedOperationException notImplemented() {

		return new UnsupportedOperationException("Not yet implemented");
	}

	public static void underConstruction(Object o) {
		System.err.println("WARNING: Under construction -> " + o.getClass().getName() + "\n\n");
	}

	public static void notTested(Object o) {
		System.err.println("WARNING: Not yet tested -> " + o.getClass().getName() + "\n\n");
	}

	@Override
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

	/**
	 * Game idea:
	 * 
	 * Keywords: Space Spacestation Survival Rougelike
	 * 
	 * you are on an empty spacestation
	 */

}
