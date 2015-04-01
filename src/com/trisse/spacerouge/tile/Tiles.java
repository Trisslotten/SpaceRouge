package com.trisse.spacerouge.tile;

import java.util.ArrayList;

import com.trisse.spacerouge.FileLoader;

public class Tiles {

	public TileTemplate[] tiles;

	public Tiles() {
		tiles = getTilesFromFile();
	}

	public TileTemplate parseTileTemplate(String str) {
		
		

		return null;
	}

	public TileTemplate[] getTilesFromFile() {

		String config = FileLoader.loadString("res/tiles.cfg");

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

				tiles.add(parseTileTemplate(currentTile));

			}

		}

		return (TileTemplate[]) tiles.toArray(new TileTemplate[tiles.size()]);
	}

}
