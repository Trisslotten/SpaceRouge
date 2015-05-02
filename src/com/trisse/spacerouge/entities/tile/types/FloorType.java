package com.trisse.spacerouge.entities.tile.types;

import com.trisse.spacerouge.entities.tile.TileType;
import com.trisse.spacerouge.graphics.*;

public class FloorType extends TileType {

	private Sprite sprite;

	public FloorType(String name, Sprite sprite, int id) {
		super(name, id);
		this.sprite = sprite;
	}

	@Override
	public Sprite defaultSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public Sprite currentSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
