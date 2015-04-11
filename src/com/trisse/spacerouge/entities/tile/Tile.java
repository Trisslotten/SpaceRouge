package com.trisse.spacerouge.entities.tile;

import java.io.Serializable;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class Tile extends Entity implements Serializable {

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

	/**
	 * 
	 */
	private static final long serialVersionUID = -1497233311167375646L;

}
