package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class DoorOpenType extends TileType {

	public DoorOpenType(String name, Sprite sprite, int id, int closesTo) {
		super(name, id, sprite);
		this.sprite = sprite;
		this.closesTo = closesTo;
	}

	@Override
	public boolean isPassable() {
		return true;
	}

	@Override
	public int level() {
		return Levels.WALL;
	}

}
