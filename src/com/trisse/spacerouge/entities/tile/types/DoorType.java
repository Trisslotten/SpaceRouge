package com.trisse.spacerouge.entities.tile.tiles;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class DoorType extends EntityType {

	@SuppressWarnings("unused")
	private Sprite open;
	private Sprite closed;

	public DoorType(String name, Sprite open, Sprite closed, int id) {
		super(name, id);
		this.open = open;
		this.closed = closed;
		floorLevel = false;
	}

}
