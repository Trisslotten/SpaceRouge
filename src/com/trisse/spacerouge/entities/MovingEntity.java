package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.Direction;

public class MovingEntity extends StaticEntity {

	private double xspd, yspd;

	public void setSpeed(double xspd, double yspd) {
		this.xspd = xspd;
		this.yspd = yspd;
	}

	public void setSpeed(Direction dir) {
		setSpeedFromDirection(dir, 1);
	}

	public void setSpeed(Direction dir, double velocity) {
		setSpeedFromDirection(dir, velocity);
	}

	protected void setSpeedFromDirection(Direction dir, double velocity) {
		switch (dir) {
		case UP:
			xspd = 0;
			yspd = -velocity;
			break;
		case RIGHT:
			xspd = velocity;
			yspd = 0;
			break;
		case DOWN:
			xspd = 0;
			yspd = velocity;
			break;
		case LEFT:
			xspd = -velocity;
			yspd = 0;
			break;
		}
	}

	public void move() {
		x += xspd;
		y += yspd;
	}

	public void stopSpeed() {
		xspd = 0;
		yspd = 0;
	}

}
