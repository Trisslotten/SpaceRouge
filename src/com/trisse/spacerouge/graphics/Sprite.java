package com.trisse.spacerouge.graphics;

public class Sprite {
	
	public static final SpriteSheet defaultSpriteSheet = new SpriteSheet(Screen.tileSize);

	public static final Sprite missing = new Sprite(0, 7);
	public static final Sprite multiItem = new Sprite(1, 7);
	public static final Sprite wall = new Sprite(2, 7);
	public static final Sprite floor = new Sprite(3, 7);
	public static final Sprite player = new Sprite(4, 7);
	public static final Sprite interior = new Sprite(5, 7);

	private int x, y;
	private SpriteSheet spriteSheet;

	public Sprite(SpriteSheet spriteSheet, int x, int y) {
		this.spriteSheet = spriteSheet;
		this.x = x;
		this.y = y;
	}

	public Sprite(int x, int y) {
		this.spriteSheet = defaultSpriteSheet;
		this.x = x;
		this.y = y;
	}

	public void bindSheet() {
		spriteSheet.bindSheet();
	}

	public void render(int xpos, int ypos) {
		spriteSheet.render(x, y, xpos, ypos);
	}

	public static final Sprite[] characters = getCharacters();

	private static Sprite[] getCharacters() {
		Sprite[] sprites = new Sprite[27];
		int y = 0;
		int x = 0;
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new Sprite(x, y);
			x++;
			if ((i + 1) % defaultSpriteSheet.sheetWidth == 0) {
				y++;
				x = 0;
			}
		}

		return sprites;
	}

	public static Sprite getChar(char c) {

		int A = 65;
		int a = 97;
		for (int i = 0; i < characters.length; i++) {
			if ((int) c == i + a || (int) c == i + A) {
				return characters[i];
			}
		}
		return characters[27];
		/*
		 * switch (c) { case 'A': case 'a': return characters[0]; case 'B': case
		 * 'b': return characters[1]; case 'C': case 'c': return characters[2];
		 * case 'D': case 'd': return characters[3]; case 'E': case 'e': return
		 * characters[4]; case 'F': case 'f': return characters[5]; case 'G':
		 * case 'g': return characters[6]; case 'H': case 'h': return
		 * characters[7]; case 'I': case 'i': return characters[8]; case 'J':
		 * case 'j': return characters[9]; case 'K': case 'k': return
		 * characters[10]; case 'L': case 'l': return characters[11]; case 'M':
		 * case 'm': return characters[12]; case 'N': case 'n': return
		 * characters[13]; case 'O': case 'o': return characters[14]; case 'P':
		 * case 'p': return characters[15]; case 'Q': case 'q': return
		 * characters[16]; case 'R': case 'r': return characters[17]; case 'S':
		 * case 's': return characters[18]; case 'T': case 't': return
		 * characters[19]; case 'U': case 'u': return characters[20]; case 'V':
		 * case 'v': return characters[21]; case 'W': case 'w': return
		 * characters[22]; case 'X': case 'x': return characters[23]; case 'Y':
		 * case 'y': return characters[24]; case 'Z': case 'z': return
		 * characters[25]; default: return characters[26]; }
		 */

	}
}
