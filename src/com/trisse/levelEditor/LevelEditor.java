package com.trisse.levelEditor;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import com.trisse.levelEditor.gui.*;
import com.trisse.levelEditor.gui.buttons.*;
import com.trisse.levelEditor.gui.elements.*;
import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class LevelEditor implements Runnable {

	public Input input = new Input();

	public Sprites sprites;

	public EntityTypePool entityTypePool;

	public List<Button> buttons = new ArrayList<Button>();
	public ArrayList<Element> elements = new ArrayList<Element>();

	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public EntityType selectedEntityType;

	private int IDCounter;

	public int xoffset = 0;
	public int yoffset = 0;

	private void init() {

		sprites = new Sprites();

		screen = new Screen(sprites);

		entityTypePool = new EntityTypePool(sprites);

		buttons = Arrays.asList(new SaveButton(), new Eraser(), new EntityGrid(entityTypePool));

		elements.add(new Spacer(sprites));

	}

	private void add() {
		entities.add(new Entity(selectedEntityType, input.xt()-xoffset(), input.yt()-yoffset(), IDCounter));
		IDCounter++;
	}

	private void remove() {

	}
	
	private int xoffset() {
		return xoffset/Screen.tileSize;
	}
	
	private int yoffset() {
		return yoffset/Screen.tileSize;
	}

	private void handleInput() {
		input.setKeys();
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
			if(input.mouseDown(1)) {
				System.out.println(input.dx());
				xoffset += input.dx();
				yoffset += input.dy();
			}
		}
	}

	private void update() {

	}

	private void render() {

		if (selectedEntityType == null) {
			screen.drawString("Erase", 48, 1);
		} else {
			screen.drawString(selectedEntityType.getName(), 48, 1);
		}

		for (Entity e : entities)
			e.render(screen,xoffset(),yoffset());
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

		//System.out.println("Starting time: " + (getTime() - start));

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
