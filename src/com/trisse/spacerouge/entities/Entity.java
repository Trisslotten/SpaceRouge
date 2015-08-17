package com.trisse.spacerouge.entities;


public abstract class Entity {

	protected int x, y;

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public boolean on(int x, int y) {
		return this.x == x && this.y == y;
	}

}
