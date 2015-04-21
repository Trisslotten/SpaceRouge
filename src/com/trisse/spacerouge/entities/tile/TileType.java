package com.trisse.spacerouge.entities.tile;

import java.io.Serializable;

import com.trisse.spacerouge.entities.EntityType;

public abstract class TileType extends EntityType implements Serializable {

	public TileType(String name) {
		super(name);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159665015474304151L;

}
