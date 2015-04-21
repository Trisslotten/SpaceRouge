package com.trisse.spacerouge.graphics;

import com.trisse.spacerouge.util.Filer;

public class Sprites {

	private static final int MISSING_SPRITE = 0;

	public Sprite[] sprites;

	public Sprites() {
		sprites = getSpritesFromFile();
	}

	public Sprite getSprite(String spriteName) {
		for (int i = 0; i < sprites.length; i++) {
			if (sprites[i].getName().equals(spriteName)) {
				return sprites[i];
			}
		}
		System.err.println("Warning: missing sprite with name: " + spriteName);
		return sprites[MISSING_SPRITE];
	}

	private Sprite[] getSpritesFromFile() {

		String config = Filer.loadString("res/sprites.spr");

		config = config.replaceAll("\\s+", " ");
		String[] splitted = config.split(" ");

		int length = splitted.length / 3;

		String[] names = new String[length];
		int[] xs = new int[length];
		int[] ys = new int[length];

		for (int i = 0; i < length; i++) {
			names[i] = splitted[i * 3];
			for (int j = 0; j < i; j++) {
				if (names[j].equalsIgnoreCase(names[i])) {
					System.err.println("Warning: Duplicate sprite name \"" + names[i] + "\" on lines: " + j + " and " + i + "\n");
				}
			}
			try {
				xs[i] = Integer.parseInt(splitted[i * 3 + 1]);
			} catch (NumberFormatException e) {
				System.err.println("Could not parse sprite x position(" + splitted[i * 3 + 2] + ") from config near: \"" + names[i] + "\" on line: " + i);
				return null;
			}
			try {
				ys[i] = Integer.parseInt(splitted[i * 3 + 2]);
			} catch (NumberFormatException e) {
				System.err.println("Could not parse sprite y position(" + splitted[i * 3 + 2] + ") from config near: \"" + names[i] + "\" on line: " + i);
				return null;
			}
			for (int j = 0; j < i; j++) {
				if (xs[j] == xs[i] && ys[j] == ys[i]) {
					System.err.println("Warning: Duplicate sprite coord (" + xs[i] + "," + ys[i] + ") on lines: " + j + " and " + i + ", and names " + names[j]
							+ " and " + names[i] + "\n");
				}
			}
		}

		Sprite[] sprites = new Sprite[length];

		for (int i = 0; i < length; i++) {
			sprites[i] = new Sprite(names[i], xs[i], ys[i]);
		}

		return sprites;
	}

}
