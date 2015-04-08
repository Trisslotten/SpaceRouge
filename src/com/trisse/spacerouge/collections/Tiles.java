package com.trisse.spacerouge.collections;

import java.util.ArrayList;

import com.trisse.spacerouge.Filer;
import com.trisse.spacerouge.graphics.Sprite;
import com.trisse.spacerouge.graphics.Sprites;
import com.trisse.spacerouge.tile.TileTemplate;
import com.trisse.spacerouge.tile.tiles.Floor;
import com.trisse.spacerouge.tile.tiles.Wall;

public class Tiles {

	public TileTemplate[] tiles;

	public Tiles(Sprites sprites) {
		tiles = getTilesFromFile(sprites);
	}

	public int length() {
		return tiles.length;
	}

	public TileTemplate get(int i) {
		return tiles[i];
	}

	public TileTemplate parseTileTemplate(String arg, Sprites sprites) {
		String cleaned = arg.replaceAll("\\s+", "");

		String[] tileData = cleaned.split(";");

		String[][] splittedString = new String[tileData.length][2];
		for (int i = 0; i < splittedString.length; i++) {
			splittedString[i] = tileData[i].split(":");
		}

		TileTemplate result = null;

		String type = "";
		String name = "";
		String spriteName = "";

		for (int i = 0; i < tileData.length; i++) {
			String variable = null;
			String value = null;
			try {
				variable = splittedString[i][0];
				value = splittedString[i][1];
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Could not parse near:\n" + arg);
			}
			switch (variable.toLowerCase()) {
			case "type":
				type = value;
				break;
			case "name":
				name = value;
				break;
			case "sprite":
				spriteName = value;
				break;
			}
		}
		Sprite sprite = sprites.getSprite(spriteName);
		switch (type) {
		case "wall":
			result = new Wall(sprite, name);
			break;
		case "floor":
			result = new Floor(sprite, name);
		}
		/*
		 * for (int i = 0; i < tileData.length; i++) { String variable =
		 * splittedString[i][0]; String value = splittedString[i][1]; switch
		 * (variable) { case "type": case "name": case "sprite": continue; }
		 * 
		 * }
		 */
		return result;
	}

	public TileTemplate[] getTilesFromFile(Sprites sprites) {

		String config = Filer.loadString("res/tiles.cfg");

		ArrayList<TileTemplate> tiles = new ArrayList<TileTemplate>();
		for (int i = 0; i < config.length(); i++) {
			char chr = config.charAt(i);

			if (chr == '{') {
				String currentTile = "";
				for (int j = i + 1; j < config.length(); j++) {
					char chr2 = config.charAt(j);
					if (chr2 != '}') {
						currentTile += chr2;
					} else {
						i = j;
						break;
					}
				}

				tiles.add(parseTileTemplate(currentTile, sprites));

			}

		}

		return (TileTemplate[]) tiles.toArray(new TileTemplate[tiles.size()]);
	}

}
