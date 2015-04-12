package com.trisse.levelEditor;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.trisse.levelEditor.gui.Button;
import com.trisse.levelEditor.gui.Element;
import com.trisse.levelEditor.gui.buttons.Eraser;
import com.trisse.levelEditor.gui.buttons.SaveButton;
import com.trisse.levelEditor.gui.buttons.TileGrid;
import com.trisse.levelEditor.gui.elements.Spacer;
import com.trisse.spacerouge.Filer;
import com.trisse.spacerouge.Input;
import com.trisse.spacerouge.collections.Entities;
import com.trisse.spacerouge.collections.Tiles;
import com.trisse.spacerouge.entities.Entity;
import com.trisse.spacerouge.entities.tile.TileTemplate;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprites;

public class LevelEditor implements Runnable {

	public Input input = new Input();

	public Sprites sprites;
	public Tiles tiles;
	public Entities entityTemplates;

	public List<Button> buttons = new ArrayList<Button>();
	public ArrayList<Element> elements = new ArrayList<Element>();

	public EditorMap map;

	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public TileTemplate selectedTile;

	public Entity selectedEntity;

	public int xoffset = 0;
	public int yoffset = 0;

	private void init() {
		double start = getTime();

		sprites = new Sprites();

		// System.out.println("Sprite loading time: " + (getTime() - start));

		screen = new Screen(sprites);

		start = getTime();

		tiles = new Tiles(sprites);

		// System.out.println("Tile loading time: " + (getTime() - start));

		//map = (EditorMap) Filer.loadObject("save/map.ser");
		map = new EditorMap();
		System.out.println("asdasdas");

		buttons = Arrays.asList(new SaveButton(), new Eraser(), new TileGrid(tiles));

		elements.add(new Spacer(sprites));
	}

	private void handleInput() {
		for (Button b : buttons)
			b.handleInput(this, input);

		int xtarget = input.xt() - (xoffset / Screen.tileSize);
		int ytarget = input.yt() - (yoffset / Screen.tileSize);

		if (input.xt() < 46 && input.mouseDown(0)) {
			if (selectedTile == null) {
				map.remove(xtarget, ytarget);
			} else {
				map.add(selectedTile, xtarget, ytarget);
			}
		}
		if (input.mouseDown(1)) {
			xoffset += input.dx();
			yoffset += input.dy();
		}

		input.setKeys();
	}

	private void update() {

	}

	private void render() {
		for (int y = 0; y < Screen.tileHeight; y++) {
			for (int x = 0; x < 45; x++) {
				screen.draw("grid", x, y, 2);
			}
		}
		map.render(screen, -xoffset / Screen.tileSize, -yoffset / Screen.tileSize);
		for (Button b : buttons)
			b.render(screen);

		for (Element e : elements)
			e.render(screen);

		if (selectedTile != null) {
			screen.draw(selectedTile.sprite, 47, 1);
			screen.drawString(selectedTile.name, 48, 1);
		} else {
			screen.drawString("Erase", 47, 1);
		}

		screen.render();
		screen.clear();
	}

	public void saveData() {
		Filer.SaveObject(map, "save/map.ser");
	}

	public void exitSave() {
		Filer.SaveObject(map, "save/map_Exit.ser");
	}

	private Screen screen;

	private static int FPS = 60;

	public int width = Screen.tileSize * Screen.tileWidth;
	public int height = Screen.tileSize * Screen.tileHeight;

	private boolean running;

	private Thread thread;

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) throws InterruptedException {
		LevelEditor game = new LevelEditor();
		game.start();
		game.thread.join();
	}

	@Override
	public void run() {
		double start = getTime();

		glinit();
		double timer = getTime();
		int frames = 0;

		init();

		System.out.println("Starting time: " + (getTime() - start));

		while (running) {
			if (Display.isCloseRequested()) {
				running = false;
				break;
			}
			handleInput();
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

				timer += 1;
				frames = 0;
			}
		}
		exitSave();
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

		Display.setTitle("Level Editor");
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
