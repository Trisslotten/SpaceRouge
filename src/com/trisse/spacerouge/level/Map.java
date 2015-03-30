package com.trisse.spacerouge.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.tile.Tile;
import com.trisse.spacerouge.tile.TileComponent;

public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2335976945981375082L;
	
	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public Map() {
		String map = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("res/map.txt"));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				map = sb.toString();
			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (map != null) {
			String[] lines = map.split("\n");
			for (int y = 0; y < lines.length; y++) {
				for (int x = 0; x < lines[y].length(); x++) {
					boolean toBreak = false;
					switch (lines[y].charAt(x)) {
					case '#':
						toBreak = true;
						break;
					case 'h':
						tiles.add(new Tile(TileComponent.wall, x, y));
						break;
					case 'w':
						tiles.add(new Tile(TileComponent.interior, x, y));
						break;
					case '.':
						tiles.add(new Tile(TileComponent.floor, x, y));
						break;
					default:
					}
					if (toBreak)
						break;
				}
			}
		} else {
			System.err.println("Could not read map");
			System.exit(0);
		}

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