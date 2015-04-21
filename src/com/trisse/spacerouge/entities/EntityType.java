package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.graphics.Screen;

public abstract class EntityType {

	protected String name;

	public EntityType(String name) {
		this.name = name;
	}

	public abstract void render(Screen screen);

	public abstract void tick(double timeScale);

}
