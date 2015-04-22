package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.graphics.*;

public abstract class EntityType {

	protected String name;

	protected int heightLevel;

	public EntityType(String name) {
		this.name = name;
	}

	public abstract Sprite currentSprite();

	public abstract void tick(double timeScale);

	public boolean isType(String str) {
		return str.equals(name);
	}

}
