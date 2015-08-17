package com.trisse.levelEditor;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import com.trisse.levelEditor.gui.*;
import com.trisse.levelEditor.gui.buttons.*;
import com.trisse.levelEditor.gui.elements.*;
import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.level.*;
import com.trisse.spacerouge.util.*;

public class LevelEditor implements Runnable {

	public static int editorWidth = Screen.tileWidth - 18;

	public Input input = new Input();

	public Sprites sprites;

	// public EntityTypePool entityTypePool;

	public List<Button> buttons = new ArrayList<Button>();
	public ArrayList<Element> elements = new ArrayList<Element>();

	public Area area;

	public EntityType selectedEntityType;

	public ActorTypePool actorPool;
	public ItemTypePool itemPool;
	public TileTypePool tilePool;

	public int level = 0;

	public boolean squareTool = false;

	public int squareX = -1;
	public int squareY = -1;
	public int startX = 0;
	public int startY = 0;

	public int xoffset = 0;
	public int yoffset = 0;

	public double brushSize = 10;

	public int viewLevel = -1;

	protected void init() {

		sprites = new Sprites();

		screen = new Screen(sprites);

		actorPool = new ActorTypePool(sprites);
		itemPool = new ItemTypePool(sprites);
		tilePool = new TileTypePool(sprites);

		buttons = Arrays.asList(new ViewLevel(this), new SquareToggle(), new SaveButton(), new Eraser(), new ExportButton(), new LoadButton(), new ExitButton(), new TileGrid(tilePool), new ActorGrid(actorPool), new ItemGrid(itemPool));

		elements.add(new Grid(sprites));
		
		area = new Area();

	}

	private void removeWithSquare() {

		/*
		 * ArrayList<Entity> toRemove = new ArrayList<Entity>(); for (int i =
		 * startY; i <= squareY; i++) { for (int j = startX; j <= squareX; j++)
		 * { int xpos = j - xoffset(); int ypos = i - yoffset(); for (Entity e :
		 * entities) {
		 * 
		 * if (e.xpos() == xpos && e.ypos() == ypos && e.type.getHeightLevel()
		 * == level) { toRemove.add(e); }
		 * 
		 * } } } for (Entity e : toRemove) { entities.remove(e); }
		 */
	}

	private void addWithSquare() {

		/*
		 * ArrayList<Entity> toAdd = new ArrayList<Entity>(); for (int i =
		 * startY; i <= squareY; i++) { for (int j = startX; j <= squareX; j++)
		 * { int xpos = j - xoffset(); int ypos = i - yoffset(); boolean canAdd
		 * = true; for (Entity e : entities) { /* if (e.xpos() == xpos &&
		 * e.ypos() == ypos && e.type.getHeightLevel() ==
		 * selectedEntityType.getHeightLevel()) { canAdd = false; }
		 * 
		 * } if (canAdd) { // toAdd.add(new Entity(selectedEntityType, xpos - i,
		 * ypos - // j)); } } } entities.addAll(toAdd);
		 */
	}

	private void addWithBrush() {

		int xpos = input.xt() - xoffset();
		int ypos = input.yt() - yoffset();
		int hb = brushSize() / 2;
		for (int i = -hb; i <= hb; i++) {
			for (int j = -hb; j <= hb; j++) {
				double distance = Math.sqrt(i * i + j * j);
				if (distance <= hb) {
					boolean canAdd = true;
					int x = xpos - i;
					int y = ypos - j;
					for (Actor e : area.actors) {
						if (e.x() == x && e.y() == y) {
							if (e.getType().isFloorLevel() == selectedEntityType.isFloorLevel()) {
								canAdd = false;
							}
						}
					}
					for (ItemEntity e : area.items) {
						if (e.x() == x && e.y() == y) {
							if (e.getType().isFloorLevel() == selectedEntityType.isFloorLevel()) {
								canAdd = false;
							}
						}
					}
					for (Tile e : area.tiles) {
						if (e.x() == x && e.y() == y) {
							if (e.getType().isFloorLevel() == selectedEntityType.isFloorLevel()) {
								canAdd = false;
							}
						}
					}
					if (canAdd) {
						if (selectedEntityType instanceof TileType) {
							TileType tile = (TileType) selectedEntityType;
							area.addNewTile(tile, x, y);
						} else if (selectedEntityType instanceof ActorType) {
							ActorType actor = (ActorType) selectedEntityType;
							area.addNewActor(actor, x, y);
						} else if (selectedEntityType instanceof ItemType) {
							ItemType item = (ItemType) selectedEntityType;
							area.addNewItem(item, x, y);
						}
					}

				}
			}
		}
	}

	private void removeWithBrush() {

		/*
		 * ArrayList<Entity> toRemove = new ArrayList<Entity>(); int xpos =
		 * input.xt() - xoffset(); int ypos = input.yt() - yoffset(); int hb =
		 * brushSize() / 2; for (int i = -hb; i <= hb; i++) { for (int j = -hb;
		 * j <= hb; j++) { double distance = Math.sqrt(i * i + j * j); if
		 * (distance <= hb) { for (Entity e : entities) { /* if (e.xpos() ==
		 * xpos - i && e.ypos() == ypos - j && e.type.getHeightLevel() == level)
		 * { toRemove.add(e); }
		 * 
		 * } }
		 * 
		 * } } for (Entity e : toRemove) { entities.remove(e); }
		 */
	}

	private int xoffset() {
		return xoffset / Screen.tileSize;
	}

	private int yoffset() {
		return yoffset / Screen.tileSize;
	}

	private int brushSize() {
		return (int) brushSize / 100;
	}

	private void handleInput() {
		int deltaWheel = Mouse.getDWheel();
		if (brushSize + deltaWheel >= 100) {
			brushSize += deltaWheel;
		}
		for (Button b : buttons)
			b.handleInput(this, input);

		if (input.xt() < editorWidth) {
			if (squareTool) {
				if (input.mouseUp(0)) {
					if (selectedEntityType != null) {
						addWithSquare();
					} else {
						removeWithSquare();
					}
					squareX = -1;
					squareY = -1;
				} else if (input.mousePressed(0)) {
					startX = input.xt();
					startY = input.yt();
				} else if (input.mouseDown(0)) {
					squareX = input.xt();
					squareY = input.yt();
				}
			} else {
				if (input.mouseDown(0)) {
					if (selectedEntityType != null) {
						addWithBrush();
					} else {
						removeWithBrush();
					}
				}
			}
			if (input.mouseDown(1)) {
				xoffset += input.dx();
				yoffset += input.dy();
			}
		}

		input.setKeys();
	}

	private void render() {
		for (Button b : buttons)
			b.render(screen);
		for (Element e : elements)
			e.render(screen);
		Sprite square = sprites.getSprite("squaremark");
		if (squareTool) {
			for (int i = startY; i <= squareY; i++) {
				for (int j = startX; j <= squareX; j++) {
					screen.draw(square, j, i, 9);
				}
			}
		} else {
			int xpos = input.xt();
			int ypos = input.yt();
			int hb = brushSize() / 2;
			for (int i = -hb; i <= hb; i++) {
				for (int j = -hb; j <= hb; j++) {
					double distance = Math.sqrt(i * i + j * j);
					if (distance <= hb && xpos + i < editorWidth) {
						screen.draw(square, xpos + i, ypos + j, 9);
					}
				}
			}
		}
		area.render(screen, -xoffset(), -yoffset());
		screen.render();
		screen.clear();
	}

	private void update() {

	}

	public void export() {

	}

	public void save() {

	}

	public void load() {

	}

	private Screen screen;

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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
			// Display.sync(FPS);
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
		Display.destroy();
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
