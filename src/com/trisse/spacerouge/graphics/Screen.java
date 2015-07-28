package com.trisse.spacerouge.graphics;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.util.*;


public class Screen {

	public static final int tileSize = 16;

	/*
	 * 1280x720 = 80x45
	 * 
	 * 1920x1080 = 120x67.5
	 */
	public static int tileWidth = 80;
	public static int tileHeight = 45;
	
	public static int guiWidth = 20;
	
	static {
		String path = "res/settings.cfg";
		String config = Filer.loadString(path);
		for (int i = 0; i < config.length(); i++) {
			char chr = config.charAt(i);
			if (chr == '{') {
				String currentActor = "";
				for (int j = i + 1; j < config.length(); j++) {
					char chr2 = config.charAt(j);
					if (chr2 != '}') {
						currentActor += chr2;
					} else {
						i = j;
						break;
					}
				}
				String cleaned = currentActor.replaceAll("\\s+", "");

				String[] settingsData = cleaned.split(";");

				String[][] splittedString = new String[settingsData.length][2];
				for (int a = 0; a < splittedString.length; a++) {
					splittedString[a] = settingsData[a].split(":");
				}
				LoadedEntity settings = LoadedEntity.newEmpty();
				for (int a = 0; a < settingsData.length; a++) {
					try {
						settings.add(splittedString[a][0], splittedString[a][1]);
					} catch (IndexOutOfBoundsException e) {
						System.err.println("Could not parse\n");
					}
				}
				String[] variables = settings.variables();
				String[] values = settings.values();
				
				
				for(int a=0;a<variables.length;a++) {
					switch (variables[a]) {
					case "width":
						tileWidth = Integer.parseInt(values[a]);
						break;
					case "height":
						tileHeight = Integer.parseInt(values[a]);
						break;
					default:
						break;
					}
				}
				
			}
		}
	}

	public int currentTileWidth;
	public int currentTileHeight;

	public Sprite[][] screen;

	public Sprites sprites;

	public Screen(int tileWidth, int tileHeight) {
		init(tileWidth, tileHeight);
	}

	public Screen(Sprites sprites) {
		init(tileWidth, tileHeight);
		this.sprites = sprites;
	}

	private void init(int tileWidth, int tileHeight) {
		currentTileHeight = tileHeight;
		currentTileWidth = tileWidth;
		screen = new Sprite[tileHeight * tileWidth][10];

	}

	public void draw(String spriteName, int x, int y) {
		Sprite sprite = null;
		for (int i = 0; i < sprites.sprites.length; i++) {
			if (spriteName.equals(sprites.sprites[i].getName())) {
				sprite = sprites.sprites[i];
				break;
			}
		}
		draw(sprite, x, y);
	}

	public void draw(String spriteName, int x, int y, int layer) {
		draw(sprites.getSprite(spriteName), x, y, layer);
	}

	public void draw(Sprite sprite, int x, int y) {
		draw(sprite, x, y, 0);
	}

	public void draw(Sprite sprite, int x, int y, int layer) {
		if (x >= 0 && x < tileWidth && y >= 0 && y < tileHeight && sprite != null) {
			int index = x + y * tileWidth;
			screen[index][layer] = sprite;
		}
	}

	public void render() {
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidth; x++) {
				for (int i = 0; i < screen[x + y * tileWidth].length; i++) {
					if (screen[x + y * tileWidth][i] != null) {
						screen[x + y * tileWidth][i].render(x, y);
					}
				}
			}
		}
	}

	public void clear() {
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[i].length; j++) {
				screen[i][j] = null;
			}

		}
	}

	public int getWidth() {
		return tileWidth * tileSize;
	}

	public int getHeight() {
		return tileHeight * tileSize;
	}

	public void drawString(String str, int x, int y) {
		drawString(str, x, y, 0);
	}
	
	public int drawString(String str, int x, int y, int layer, int width) {
		if(str.length()<=width) {
			drawString(str,x,y);
			return 1;
		}
		int current = width;
		for(int i=width;str.charAt(i)!= ' ' && str.charAt(i) != '_'; --i) {
			current = i-1;
		}
		String first = str.substring(0, current);
		drawString(first,x,y);
		String rest = str.substring(current, str.length());
		rest = rest.substring(1, rest.length());
		return drawString(rest, x, y + 1, layer, width) + 1;
	}
	
	public int drawStringInGui(String str, int x, int y) {
		return drawString(str,x,y,0,guiWidth);
	}

	public void drawString(String str, int x, int y, int layer) {
		for (int i = 0; i < str.length(); i++) {
			char crnt = str.charAt(i);

			try {
				int num = Integer.parseInt(String.valueOf(crnt));
				draw(Sprite.getNum(num), x + i, y, layer);
			} catch (NumberFormatException e) {
				draw(Sprite.getChar(str.charAt(i)), x + i, y, layer);
			}
		}
	}

	public int getRows(String str, int width) {
		if(str.length()<=width) {
			return 1;
		}
		int current = width;
		for(int i=width;str.charAt(i)!= ' ' && str.charAt(i) != '_'; --i) {
			current = i-1;
		}
		String rest = str.substring(current, str.length());
		rest = rest.substring(1, rest.length());
		return getRows(rest, width) + 1;
	}

}

























