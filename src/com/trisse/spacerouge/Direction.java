package com.trisse.spacerouge;

public enum Direction {

	UP, DOWN, LEFT, RIGHT;

	public int xspd() {
		return xspd(1);
	}

	public int yspd() {
		return yspd(1);
	}

	public int xspd(int speed) {
		switch (this) {
		case LEFT:
			return -speed;
		case RIGHT:
			return speed;
		default:
			return 0;
		}
	}

	public int yspd(int speed) {
		switch (this) {
		case UP:
			return -speed;
		case DOWN:
			return speed;
		default:
			return 0;
		}
	}
}
