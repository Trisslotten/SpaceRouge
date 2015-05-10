package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class DoorType extends TileType {

	@SuppressWarnings("unused")
	private Sprite open;
	private Sprite closed;
	
	private boolean isClosed;

	public DoorType(String name, Sprite open, Sprite closed, int id) {
		super(name, id);
		this.open = open;
		this.closed = closed;
		floorLevel = false;
	}

	@Override
	public Sprite defaultSprite() {
		return closed;
	}

	@Override
	public Sprite currentSprite() {
		return closed;
	}

}
