package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.graphics.*;

public abstract class EntityType {

	protected String name;
	protected int id;

	protected boolean floorLevel;

	public EntityType(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public boolean isType(String str) {
		return str.equalsIgnoreCase(name);
	}

	public boolean isFloorLevel() {
		return floorLevel;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public abstract Sprite currentSprite();

}