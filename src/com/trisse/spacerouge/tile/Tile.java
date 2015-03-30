
package com.trisse.spacerouge.tile;

import com.trisse.spacerouge.graphics.Sprite;

public class Tile {
	
	public int x, y;
	
	public TileComponent tileTemplate;
	
	public Tile(TileComponent tileTemplate, int x, int y) {
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
