package com.trisse.spacerouge.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.tile.Tile;
import com.trisse.spacerouge.tile.TileTemplate;

public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2335976945981375082L;

	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public Map() {
		
	}

	public void render(Screen screen, Player player) {
		int xoffset = player.xpos();
		int yoffset = player.ypos();

		for (Tile t : tiles) {
			int x = t.x - xoffset + Screen.tileWidth / 2;
			int y = t.y - yoffset + Screen.tileHeight / 2;
			screen.draw(t.getSprite(), x, y, 0);
		}
	}

	public boolean isWall(int x, int y) {
		for (Tile t : tiles) {
			if (t.x == x && t.y == y) {
				if (t.isWall()) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
