package com.trisse.levelEditor;

import com.trisse.spacerouge.entities.tile.Tile;
import com.trisse.spacerouge.entities.tile.TileTemplate;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.level.Map;

public class EditorMap extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1142366806210790075L;

	public void add(TileTemplate tile, int x, int y) {
		if (tile != null) {

			for (int i = 0; i < tiles.size(); i++) {
				Tile t = tiles.get(i);
				if (t.x == x && t.y == y) {
					tiles.set(i, new Tile(tile, x, y));
					return;
				}
			}

			tiles.add(new Tile(tile, x, y));
		}
	}

	public void render(Screen screen, int xoffset, int yoffset) {

		for (Tile t : tiles) {
			int x = t.x - xoffset;
			if (x < 46 && x >= 0) {
				int y = t.y - yoffset;
				if (y >= 0 && y < Screen.tileHeight)
					screen.draw(t.getSprite(), x, y, 0);
			}
		}
	}

	public void remove(int x, int y) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);
			if (t.x == x && t.y == y) {
				tiles.remove(i);
			}
		}
	}

}
