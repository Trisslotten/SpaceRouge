package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class WallType extends TileType {

	public WallType(String name, Sprite sprite, int id) {
		super(name, id, sprite);
		floorLevel = false;
	}

	public int level() {
		return Levels.WALL;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

}
