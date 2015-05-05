package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.Direction;
import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.graphics.Screen;

public class Actor {

	protected Action action;

	protected ActorType type;

	protected int energy = 7;

	protected int cost = 12;

	protected int energySpeed = 5;

	public boolean isPlayer = false;

	protected int x, y;

	public Actor() {

	}

	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void think() {
		energy += energySpeed;
		action = new WalkAction(this, Direction.DOWN);
	}

	public void setNextAction(Action action) {
		this.action = action;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		screen.draw("player", x, y);
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public void move(Direction dir) {
		x += dir.xspd();
		y += dir.yspd();
	}

	public Action getAction() {
		if (energy >= cost) {
			energy -= cost;
			Action action = this.action;
			this.action = null;
			return action;
		} else {
			return null;
		}
	}
}
