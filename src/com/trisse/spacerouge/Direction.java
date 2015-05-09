package com.trisse.spacerouge;

public enum Direction {

	UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT, NONE;

	public int xspd() {
		return xspd(1);
	}

	public int yspd() {
		return yspd(1);
	}

	public int xspd(int speed) {
		switch (this) {
		case LEFT:
		case DOWN_LEFT:
		case UP_LEFT:
			return -speed;
		case RIGHT:
		case UP_RIGHT:
		case DOWN_RIGHT:
			return speed;
		default:
			return 0;
		}
	}

	public int yspd(int speed) {
		switch (this) {
		case UP:
		case UP_RIGHT:
		case UP_LEFT:
			return -speed;
		case DOWN:
		case DOWN_RIGHT:
		case DOWN_LEFT:
			return speed;
		default:
			return 0;
		}
	}
}
