package com.trisse.spacerouge.entities.tile;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class TileType extends EntityType {

	public int opensTo, closesTo;
	
	private double o2Level;

	public TileType(String name, int id, Sprite sprite) {
		super(name, id, sprite);
	}

	public abstract int level();

	public abstract boolean isPassable();

	public void render(Screen screen, int x, int y) {
		getSprite().render(x, y);
	}

	public double getO2Level() {
		if(level()==Levels.WALL) {
			return 0;
		} else {
			return o2Level;
		}
	}

}
