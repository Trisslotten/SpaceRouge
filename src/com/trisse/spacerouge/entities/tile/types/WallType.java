package com.trisse.spacerouge.entities.tile.types;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 7257316785452670891L;

	@Override
	public Sprite currentSprite() {
		return sprite;
	}

}
