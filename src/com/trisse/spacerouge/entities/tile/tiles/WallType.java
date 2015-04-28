package com.trisse.spacerouge.entities.tile.tiles;

import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class WallType extends TileType {

	Sprite sprite;

	public WallType(String name, Sprite sprite, int id) {
		super(name, id);
		this.sprite = sprite;
		this.heightLevel = 1;
	}

	@Override
	public Sprite currentSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public void tick(double timeScale) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sprite defaultSprite() {
		return sprite;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7257316785452670891L;

}
