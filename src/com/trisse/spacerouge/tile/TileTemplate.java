package com.trisse.spacerouge.tile;

import java.io.Serializable;

import com.trisse.spacerouge.graphics.Sprite;

public class TileTemplate implements Serializable {

	public boolean isWall;

	public String name;

	public Sprite sprite;

	public TileTemplate(Sprite sprite, String name, boolean isWall) {
		this.name = name;
		this.sprite = sprite;
		this.isWall = isWall;
	}

}
