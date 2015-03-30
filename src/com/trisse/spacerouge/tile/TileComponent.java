package com.trisse.spacerouge.tile;

import com.trisse.spacerouge.graphics.Sprite;

public class TileComponent {

	public static final TileComponent wall;
	public static final TileComponent interior;
	public static final TileComponent floor;
	static {
		wall = new TileComponent(Sprite.wall, true);
		floor = new TileComponent(Sprite.floor, false);
		interior = new TileComponent(Sprite.interior,true);
	}

	public boolean isWall;

	public Sprite sprite;

	public TileComponent(Sprite sprite, boolean isWall) {
		this.sprite = sprite;
		this.isWall = isWall;
	}

}

