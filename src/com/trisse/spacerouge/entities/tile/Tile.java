package com.trisse.spacerouge.entities.tile;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class Tile extends Entity {

	private TileType type;

	public Tile(TileType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void render(Screen screen, int xpos, int ypos) {
		screen.draw(type.getSprite(), x() + xpos, y() + ypos, type.level());
	}

	public boolean isPassable() {
		return type.isPassable();
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
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
	
	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
	
}