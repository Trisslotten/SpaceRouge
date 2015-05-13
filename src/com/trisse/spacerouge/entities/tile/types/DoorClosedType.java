package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class DoorClosedType extends TileType {

	private Sprite sprite;

	public DoorClosedType(String name, Sprite sprite, int id, int opensTo) {
		super(name, id);
		this.sprite = sprite;
		floorLevel = false;
		this.opensTo = opensTo;
	}

	@Override
	public Sprite defaultSprite() {
		return sprite;
	}

	@Override
	public Sprite currentSprite() {
		return sprite;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public int level() {
		return Levels.WALL;
	}

}
