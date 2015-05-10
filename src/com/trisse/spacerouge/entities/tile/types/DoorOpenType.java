package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class DoorOpenType extends TileType {

	private Sprite sprite;

	public DoorOpenType(String name, Sprite sprite, int id, int closesTo) {
		super(name, id);
		this.sprite = sprite;
		floorLevel = false;
		this.closesTo = closesTo;
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
		return true;
	}

	@Override
	public int level() {
		return 1;
	}

}