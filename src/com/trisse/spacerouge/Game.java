package com.trisse.spacerouge;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.trisse.spacerouge.action.Action;
import com.trisse.spacerouge.action.WalkAction;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.entities.actor.ActorTypePool;
import com.trisse.spacerouge.entities.actor.types.Player;
import com.trisse.spacerouge.entities.item.ItemTypePool;
import com.trisse.spacerouge.entities.item.Items;
import com.trisse.spacerouge.entities.tile.Tile;
import com.trisse.spacerouge.entities.tile.TileTypePool;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprites;
import com.trisse.spacerouge.util.Input;

public class Game implements Runnable {

	public Input input = new Input();
	public Sprites sprites;

	public ActorTypePool actorPool;
	public ItemTypePool itemPool;
	public TileTypePool tilePool;

	private boolean waitingForPlayer = false;

	ArrayList<Tile> tiles = new ArrayList<Tile>();
	ArrayList<Actor> actors = new ArrayList<Actor>();

	ArrayList<Tile> activeTiles = new ArrayList<Tile>();

	Items items = new Items();

	// current actor index
	int cai = 0;

	protected void init() {
		sprites = new Sprites();
		screen = new Screen(sprites);

		actorPool = new ActorTypePool(sprites);
		itemPool = new ItemTypePool(sprites);
		tilePool = new TileTypePool(sprites);

		actors.add(new Player(20, 20));
		actors.add(new Actor(20, 20));
		actors.add(new Actor(20, 20));
		actors.add(new Actor(20, 21));
		actors.add(new Actor(20, 22));
		actors.add(new Actor(20, 23));
		actors.add(new Actor(20, 24));
		actors.add(new Actor(20, 25));
		actors.add(new Actor(20, 26));
		currentActor = actors.get(cai);
		// gameState = new MainMenuState(sprites, entityList);
		Keyboard.enableRepeatEvents(false);
	}

	protected void handleInput() {
		int key = Keyboard.getEventKey();
		if (Input.controlPressed()) {
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
				walk(null);
				break;
			}
		}
	}

	protected void walk(Direction dir) {
		Actor actor = actors.get(cai);
		actor.setNextAction(new WalkAction(actor, dir));
	}

	private Actor currentActor;

	protected void update() {
		Action action;
		// do {
		currentActor.think();
		if (currentActor.isPlayer) {
			handleInput();
		}
		action = currentActor.getAction();
		if (action == null) {
			if (!currentActor.isPlayer) {
				cai = (cai + 1) % actors.size();
			}
		} else {
			action.perform();
			cai = (cai + 1) % actors.size();
		}
		currentActor = actors.get(cai);
		// } while (!currentActor.isPlayer && action != null);
	}

	protected void render() {
		for (Tile t : tiles) {
			t.render(screen, 0, 0);
		}
		items.render(screen, 0, 0);

		for (Actor actor : actors)
			actor.render(screen, 0, 0);

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

	public final double tickInterval = 0.2;
	public final double tickIntervalFast = 0.05;
	public final int slowToFastTickCount = 1;
	public int tickCounter = 0;
	public boolean canTick = false;
	public double lastTick = Game.getTime();

	@SuppressWarnings("unused")
	private int tickButton;

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
			/*
			 * if (canTick) { double tickInterval = tickCounter >
			 * slowToFastTickCount ? tickIntervalFast : this.tickInterval; if
			 * (getTime() - lastTick > tickInterval) { tickCounter++; lastTick =
			 * getTime(); tick(); } canTick = false; }
			 */
			update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
			Display.update();
			frames++;
			Display.sync(FPS);
			/*
			 * if (delta <= 1.0 / FPS) { try { Thread.sleep((long) ((1.0 / FPS -
			 * delta) * 1000)); } catch (InterruptedException e) {
			 * e.printStackTrace(); } }
			 */
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
