package com.trisse.spacerouge.tile;

import java.io.Serializable;

import com.trisse.spacerouge.graphics.Sprite;

public class Tile implements Serializable {

	public int x, y;

	public TileTemplate tileTemplate;

	public Tile(TileTemplate tileTemplate, int x, int y) {
		this.tileTemplate = tileTemplate;
		this.x = x;
		this.y = y;
	}

	public boolean isWall() {
		return tileTemplate.isWall;
	}

	public Sprite getSprite() {
		return tileTemplate.sprite;
	}

}
