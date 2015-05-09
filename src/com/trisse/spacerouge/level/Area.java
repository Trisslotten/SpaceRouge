package com.trisse.spacerouge.level;

import java.util.ArrayList;

import com.trisse.spacerouge.entities.tile.Tile;
import com.trisse.spacerouge.entities.tile.TileTypePool;
import com.trisse.spacerouge.graphics.Screen;

public class Area {

	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	public Area(TileTypePool tilePool) {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (j == 0 || j == 10 || i == 0 || i == 10) {
					tiles.add(new Tile(tilePool.getType("hull"), i, j));
				} else {
					tiles.add(new Tile(tilePool.getType("floor"), i, j));
				}
			}

		}
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		for (Tile t : tiles) {
			t.render(screen, xoffset, yoffset);
		}
	}

	public boolean isPassable(int x, int y) {
		for (Tile t : tiles) {
			if (t.x() == x && t.y() == y && t.isPassable()) {
				return false;
			}
		}
		return true;
	}

}
