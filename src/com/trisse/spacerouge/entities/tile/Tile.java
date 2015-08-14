package com.trisse.spacerouge.entities.tile;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class Tile extends Entity {

	private TileType type;

	public Tile(TileType type) {
		this.type = type;
	}

	public void render(Screen screen, int x, int y) {
		System.out.println("Drawing: " + type.getName());
		System.out.println("x: " + x + " y: " + y);
		screen.draw(type.getSprite(), x, y, type.level());
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

	public void open(TileTypePool tilePool) {
		TileType newType = tilePool.getType((type.opensTo));
		if (newType != null)
			setType(newType);
	}

	public void close(Game game) {
		TileType newType = game.tilePool.getType((type.closesTo));
		if (newType != null) {
			setType(newType);
		}
	}
}
