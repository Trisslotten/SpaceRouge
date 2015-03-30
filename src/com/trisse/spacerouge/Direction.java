package com.trisse.spacerouge;

public enum Direction {

	UP, DOWN, LEFT, RIGHT;

	public double xspd(double speed) {
		switch (this) {
		case LEFT:
			return -speed;
		case RIGHT:
			return speed;
		default:
			return 0;
		}
	}

	public double yspd(double speed) {
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
