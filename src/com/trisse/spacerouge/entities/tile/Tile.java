package com.trisse.spacerouge.entities.tile;

import com.trisse.spacerouge.graphics.*;

public class Tile {

	private int x, y;

	private TileType type;

	public Tile(TileType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		screen.draw(type.currentSprite(), x - xoffset, y - yoffset, type.level());
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public boolean isPassable() {
		return type.isPassable();
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type2) {
		type = type2;
	}

}
