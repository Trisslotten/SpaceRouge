package com.trisse.spacerouge.entities.actor;

import java.util.Random;

import com.trisse.spacerouge.Direction;
import com.trisse.spacerouge.action.Action;
import com.trisse.spacerouge.action.WalkAction;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.level.Area;

public class Actor {

	protected Action action;

	protected ActorType type;

	public boolean isPlayer = false;
	protected int x, y;

	private Area area;

	static Random rand = new Random();

	public Actor(int x, int y, Area area) {
		this.x = x;
		this.y = y;
		this.area = area;
	}

	public void init() {

	}

	public void think() {
		switch (rand.nextInt(4)) {
		case 0:
			walk(Direction.UP);
			break;
		case 1:
			walk(Direction.DOWN);
			break;
		case 2:
			walk(Direction.LEFT);
			break;
		case 3:
			walk(Direction.RIGHT);
			break;
		default:
			walk(Direction.NONE);
		}
	}

	public void walk(Direction dir) {
		setNextAction(new WalkAction(this, area, dir));
	}

	public void setNextAction(Action action) {
		this.action = action;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		screen.draw("player", x - xoffset, y - yoffset);
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
		Action action = this.action;
		this.action = null;
		return action;
	}
}
