package com.trisse.spacerouge.entities;

import java.io.*;

import com.trisse.spacerouge.components.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5676472648486553940L;

	protected double x, y, speed;
	protected Direction direction;

	public EntityType type;

	protected Collision collision = new Collision();
	protected Physics physics = new Physics();

	public Entity(EntityType type) {
		this.type = type;
	}

	public Entity(EntityType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void tick(double timeScale) {
		type.tick(timeScale);
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		screen.draw(type.currentSprite(), xpos() - xoffset, ypos() - yoffset, type.getHeightLevel());
	}

	public int typeID() {
		return type.id;
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
