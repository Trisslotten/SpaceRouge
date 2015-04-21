package com.trisse.spacerouge;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.trisse.spacerouge.collections.EntityList;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprites;
import com.trisse.spacerouge.level.Level;
import com.trisse.spacerouge.util.Input;

public class Game implements Runnable {

	public Input input = new Input();

	public Sprites sprites;
	
	public EntityList entityList;

	public Level level;

	private void init() {

		sprites = new Sprites();

		screen = new Screen(sprites);
		
		entityList = new EntityList(sprites);

		level = new Level(sprites);

		level.init();
	}

	private void handleInput() {
		input.setKeys();

		level.handleInput(this);
	}

	private void tick() {
		level.tick(this);
	}

	private void render() {
		level.render(screen);

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

	public void canTick(int button) {
		tickButton = button;
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
			handleInput();
			if (canTick) {
				double tickInterval = tickCounter > slowToFastTickCount ? tickIntervalFast : this.tickInterval;
				if (getTime() - lastTick > tickInterval) {
					tickCounter++;
					lastTick = getTime();
					tick();
				}
				canTick = false;
				tickButton = -1;
			} else {
				tickCounter = 0;
			}
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
