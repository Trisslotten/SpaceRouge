package com.trisse.spacerouge.tile;

import com.trisse.spacerouge.graphics.Sprite;

public class TileTemplate {
	
	public static final TileTemplate[] tiles = getTiles();
	
	public static final TileTemplate[] getTiles() {
		return new TileTemplate[] {
			new TileTemplate(Sprite.wall, "Wall", true),
			new TileTemplate(Sprite.floor, "Floor", false),
			new TileTemplate(Sprite.interior, "Interior wall", true)
		};
	}

	public boolean isWall;

	public String name;

	public Sprite sprite;

	public TileTemplate(Sprite sprite, String name, boolean isWall) {
		this.name = name;
		this.sprite = sprite;
		this.isWall = isWall;
	}

	public static TileTemplate getFromChar(char chr) {
		switch (chr) {
		case 'h':
			return tiles[0];
		case 'w':
			return tiles[2];
		case '.':
			return tiles[1];
		default:
			return null;
		}
	}

}
