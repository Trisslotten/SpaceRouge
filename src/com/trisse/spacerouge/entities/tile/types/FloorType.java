package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.tile.TileType;
import com.trisse.spacerouge.graphics.*;

public class FloorType extends TileType {

	public FloorType(String name, Sprite sprite, int id) {
		super(name, id, sprite);
		this.sprite = sprite;
	}

	@Override
	public boolean isPassable() {
		return true;
	}

	@Override
	public int level() {
		// TODO Auto-generated method stub
		return Levels.FLOOR;
	}

}
