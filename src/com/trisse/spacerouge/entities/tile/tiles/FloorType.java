package com.trisse.spacerouge.entities.tile.tiles;

import com.trisse.spacerouge.entities.tile.TileType;
import com.trisse.spacerouge.graphics.*;

public class FloorType extends TileType {

	private Sprite sprite;

	public FloorType(String name, Sprite sprite) {
		super(name);
		this.sprite = sprite;
	}

	@Override
	public Sprite defaultSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick(double timeScale) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3358289524794563980L;

	@Override
	public Sprite currentSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
