package com.trisse.spacerouge.entities.tile;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class TileType extends EntityType {

	public int opensTo, closesTo;

	public TileType(String name, int id) {
		super(name, id);
		floorLevel = true;
	}

	public abstract int level();
	
	public abstract Sprite defaultSprite();
	
	public abstract boolean isPassable();

}
