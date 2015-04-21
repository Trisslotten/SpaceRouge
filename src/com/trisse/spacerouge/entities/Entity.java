package com.trisse.spacerouge.entities;

import java.io.Serializable;

import com.trisse.spacerouge.components.Collision;
import com.trisse.spacerouge.components.Physics;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprite;
import com.trisse.spacerouge.util.Direction;

public class Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5676472648486553940L;

	protected String name;
	protected double x, y, speed;
	protected int heightLevel;
	protected Direction direction;

	protected EntityType entityType;

	protected Collision collision = new Collision();
	protected Physics physics = new Physics();

	public void tick(double timeScale) {
		entityType.tick(timeScale);
	}

	public void render(Screen screen) {
		entityType.render(screen);
	}

	public void move() {
		x += direction.xspd(speed);
		y += direction.yspd(speed);
	}

	public void handleCollision(Entity entity) {
		if (entity.xpos() == nextXpos() && entity.xpos() == nextYpos()) {
			collision.handle(this);
		}
	}

	public void stopSpeed() {
		speed = 0;
	}

	public void setSpeed(double speed, Direction dir) {
		setSpeed(speed);
		setSpeed(dir);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setSpeed(Direction dir) {
		this.direction = dir;
	}

	public int nextXpos() {
		return (int) Math.round(x + direction.xspd(speed));
	}

	public int nextYpos() {
		return (int) Math.round(y + direction.yspd(speed));
	}

	public int xpos() {
		return (int) Math.round(x);
	}

	public int ypos() {
		return (int) Math.round(y);
	}

}
