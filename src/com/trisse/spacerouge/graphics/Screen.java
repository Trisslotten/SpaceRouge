package com.trisse.spacerouge.graphics;


public class Screen {

	// The pixel-size of the tiles
	public static final int tileSize = 16;

	/*
	 * 1280x720 = 80x45
	 * 
	 * 1920x1080 = 120x67.5
	 */

	// The dimensions of the windows in tileSize
	public static int tileWidth = 80;
	public static int tileHeight = 45;

	public Sprite[][] screen;

	public Sprites sprites;

	public Screen(Sprites sprites) {
		init(tileWidth, tileHeight);
		this.sprites = sprites;
	}

	
	private void init(int tileWidth, int tileHeight) {
		screen = new Sprite[tileHeight * tileWidth][10];
	}

	// draws a sprite with String par as name on the coords x and y
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

	// draws a sprite with String par as name on the coords x, y and layer
	public void draw(String spriteName, int x, int y, int layer) {
		draw(sprites.getSprite(spriteName), x, y, layer);
	}

	// draws the sprite on x and y
	public void draw(Sprite sprite, int x, int y) {
		draw(sprite, x, y, 0);
	}

	// draws the sprite on x, y and layer
	public void draw(Sprite sprite, int x, int y, int layer) {
		if (x >= 0 && x < tileWidth && y >= 0 && y < tileHeight && sprite != null) {
			int index = x + y * tileWidth;
			screen[index][layer] = sprite;
		}
	}

	// draws all sprites on the screen
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

	// clears all sprites on the screen
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

	// draws the string on x and y
	public void drawString(String str, int x, int y) {
		drawString(str, x, y, layerSize());
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

	public int layerSize() {
		return screen[0].length-1;
	}

}
