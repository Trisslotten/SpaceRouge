package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.graphics.*;

public class Actor {

	protected Action currentAction;

	protected ActorType type;

	protected int energy;

	protected int x, y;

	public void think() {
		currentAction = new WalkAction(this);

	}

	public Action getAction() {
		Action action = currentAction;
		currentAction = null;
		return action;
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

}
