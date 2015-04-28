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
import com.trisse.levelEditor.gui.buttons.EntityGrid;
import com.trisse.levelEditor.gui.buttons.Eraser;
import com.trisse.levelEditor.gui.buttons.ExitButton;
import com.trisse.levelEditor.gui.buttons.ExportButton;
import com.trisse.levelEditor.gui.buttons.LoadButton;
import com.trisse.levelEditor.gui.buttons.SaveButton;
import com.trisse.levelEditor.gui.elements.Spacer;
import com.trisse.spacerouge.collections.EntityTypePool;
import com.trisse.spacerouge.entities.Entity;
import com.trisse.spacerouge.entities.EntityType;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprites;
import com.trisse.spacerouge.level.LevelEditorMap;
import com.trisse.spacerouge.util.Input;

public class LevelEditor implements Runnable {

	public Input input = new Input();

	public Sprites sprites;

	public EntityTypePool entityTypePool;

	public List<Button> buttons = new ArrayList<Button>();
	public ArrayList<Element> elements = new ArrayList<Element>();

	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public EntityType selectedEntityType;

	public LevelEditorMap map = new LevelEditorMap();

	public int xoffset = 0;
	public int yoffset = 0;

	private void init() {

		sprites = new Sprites();

		screen = new Screen(sprites);

		entityTypePool = new EntityTypePool(sprites);

		buttons = Arrays.asList(new SaveButton(), new Eraser(), new EntityGrid(entityTypePool), new ExportButton(), new LoadButton(), new ExitButton());

		elements.add(new Spacer(sprites));

	}

	public void export() {
		map.export(entityTypePool);
	}

	public void save() {
		map.save(entities);
	}

	public void load() {
		map.load();
	}

	private void add() {
		boolean canAdd = true;
		int xpos = input.xt() - xoffset();
		int ypos = input.yt() - yoffset();

		for (Entity e : entities) {
			if (e.xpos() == xpos && e.ypos() == ypos) {
				canAdd = false;
			}
		}
		if (canAdd) {
			entities.add(new Entity(selectedEntityType, xpos, ypos));
		}
	}

	private void remove() {
		int xpos = input.xt() - xoffset();
		int ypos = input.yt() - yoffset();
		int removeIndex = -1;

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e.xpos() == xpos && e.ypos() == ypos) {
				removeIndex = i;
			}
		}
		if (removeIndex >= 0) {
			entities.remove(removeIndex);
		}
	}

	private int xoffset() {
		return xoffset / Screen.tileSize;
	}

	private int yoffset() {
		return yoffset / Screen.tileSize;
	}

	private void handleInput() {
		for (Button b : buttons)
			b.handleInput(this, input);

		if (input.xt() < 46) {
			if (input.mouseDown(0)) {
				if (selectedEntityType != null) {
					add();
				} else {
					remove();
				}
			}
			if (input.mouseDown(1)) {
				xoffset += input.dx();
				yoffset += input.dy();
			}
		}
		input.setKeys();
	}

	private void update() {

	}

	private void render() {

		if (selectedEntityType == null) {
			screen.drawString("Erase", 47, 1);
		} else {
			screen.drawString(selectedEntityType.getName(), 46, 1);
		}

		for (Entity e : entities)
			e.render(screen, -xoffset(), -yoffset());
		for (Button b : buttons)
			b.render(screen);
		for (Element e : elements)
			e.render(screen);
		screen.render();
		screen.clear();
	}

	public void saveData() {

	}

	public void exitSave() {

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

	public void stop() {
		System.exit(0);
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

		// System.out.println("Starting time: " + (getTime() - start));

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
			//Display.sync(FPS);
			/*
			 * if (delta <= 1.0 / FPS) { try { Thread.sleep((long) ((1.0 / FPS -
			 * delta) * 1000)); } catch (InterruptedException e) {
			 * e.printStackTrace(); } }
			 */
			if (getTime() > timer + 1) {
				Display.setTitle("Entities: " + entities.size() + " FPS: " + frames);
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
