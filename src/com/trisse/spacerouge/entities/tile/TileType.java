package com.trisse.spacerouge.entities.tile;

import java.io.*;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class TileType extends EntityType implements Serializable {

	public TileType(String name, int id) {
		super(name, id);
		heightLevel = 0;
	}

	public abstract Sprite defaultSprite();

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159665015474304151L;

}
