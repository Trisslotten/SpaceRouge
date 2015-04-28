package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.graphics.*;

public abstract class EntityType {

	protected String name;
	protected int id;

	protected int heightLevel;

	public EntityType(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public abstract Sprite currentSprite();

	public abstract void tick(double timeScale);

	public boolean isType(String str) {
		return str.equals(name);
	}

	public void render(Screen screen) {
		// TODO Auto-generated method stub

	}

	public int getHeightLevel() {
		return heightLevel;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

}
