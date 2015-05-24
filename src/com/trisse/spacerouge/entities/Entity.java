package com.trisse.spacerouge.entities;

public abstract class Entity {

	protected boolean isVisible = true;
	protected boolean isTransparent;

	protected int x, y;

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isTransparent() {
		return isTransparent;
	}

	public void setTransparent(boolean isTransparent) {
		this.isTransparent = isTransparent;
	}
}
