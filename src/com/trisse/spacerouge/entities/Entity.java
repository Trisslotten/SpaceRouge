package com.trisse.spacerouge.entities;

import java.io.*;

import com.trisse.spacerouge.graphics.*;

@SuppressWarnings("serial")
public abstract class Entity implements Serializable {

	protected double x, y;

	public EntityType type;

	public Entity(EntityType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public int x() {
		return (int) x;
	}

	public int y() {
		return (int) y;
	}

}
