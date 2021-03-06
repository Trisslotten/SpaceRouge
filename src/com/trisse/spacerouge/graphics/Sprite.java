package com.trisse.spacerouge.graphics;

import java.io.*;

public class Sprite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1759677685485810983L;

	public static final SpriteSheet defaultSpriteSheet = new SpriteSheet(Screen.tileSize);

	private String name;
	private int x, y;
	private SpriteSheet spriteSheet;

	public Sprite(SpriteSheet spriteSheet, String name, int x, int y) {
		this.spriteSheet = spriteSheet;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public Sprite(String name, int x, int y) {
		this.spriteSheet = defaultSpriteSheet;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public void bindSheet() {
		spriteSheet.bindSheet();
	}

	public void render(int xpos, int ypos) {
		spriteSheet.render(x, y, xpos, ypos);
	}

	public String getName() {
		return name;
	}

	public static final Sprite[] characters = getCharacters();

	private static Sprite[] getCharacters() {
		Sprite[] sprites = new Sprite[27];
		int y = 0;
		int x = 0;
		for (int i = 0; i < sprites.length; i++) {
			char name = (char) (65 + i);
			sprites[i] = new Sprite(String.valueOf(name), x, y);
			x++;
			if ((i + 1) % defaultSpriteSheet.sheetWidth == 0) {
				y++;
				x = 0;
			}
		}

		return sprites;
	}

	private static final Sprite[] numbers = getNumbers();

	public static Sprite getChar(char c) {

		int A = 65;
		int a = 97;
		for (int i = 0; i < characters.length; i++) {
			if ((int) c == i + a || (int) c == i + A) {
				return characters[i];
			}
		}
		return characters[26];
	}

	public static Sprite getNum(int num) {
		return numbers[num];
	}

	private static Sprite[] getNumbers() {
		Sprite[] sprites = new Sprite[10];
		for (int x = 0; x < 10; x++) {
			char name = (char) (48 + x);
			sprites[x] = new Sprite(String.valueOf(name), x, 3);
		}
		return sprites;
	}
}
