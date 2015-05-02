package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.*;

@SuppressWarnings("serial")
public abstract class Movable extends Entity {

	public Movable(EntityType type, int x, int y) {
		super(type, x, y);
	}

	protected Direction direction;
	
	

}
