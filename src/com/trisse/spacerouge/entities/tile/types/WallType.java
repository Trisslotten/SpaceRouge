package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class WallType extends TileType {

	Sprite sprite;

	public WallType(String name, Sprite sprite, int id) {
		super(name, id);
		this.sprite = sprite;
		floorLevel = false;
	}

	@Override
	public Sprite defaultSprite() {
		return sprite;
	}

	public int level() {
		return Levels.WALL;
	}

	@Override
	public Sprite currentSprite() {
		return sprite;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

}
