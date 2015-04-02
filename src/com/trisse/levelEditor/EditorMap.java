package com.trisse.levelEditor;

import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.level.Map;
import com.trisse.spacerouge.tile.Tile;
import com.trisse.spacerouge.tile.TileTemplate;

public class EditorMap extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1012589539129424419L;

	public void add(TileTemplate tile, int x, int y) {
		tiles.add(new Tile(tile, x, y));
	}

	public void render(Screen screen, int xoffset, int yoffset) {

		for (Tile t : tiles) {
			int x = t.x - xoffset + Screen.tileWidth / 2;
			int y = t.y - yoffset + Screen.tileHeight / 2;
			screen.draw(t.getSprite(), x, y, 0);
		}
	}

}
