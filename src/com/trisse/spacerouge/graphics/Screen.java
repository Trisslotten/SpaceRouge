package com.trisse.spacerouge.graphics;

public class Screen {

	public static final int tileSize = 16;

	public static int tileWidth = 64;
	public static int tileHeight = 36;

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
		screen = new Sprite[tileHeight * tileWidth][3];

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

	public void drawString(String str, int x, int y, int layer) {
		for (int i = 0; i < str.length(); i++) {
			draw(Sprite.getChar(str.charAt(i)), x + i, y, layer);
		}
	}

}
