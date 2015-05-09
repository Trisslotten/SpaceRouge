package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.Area;

public class Player extends Actor {

	public Player(int x, int y, Area area) {
		super(x, y, area);
	}

	public void init() {
		isPlayer = true;
		energySpeed = 2;
	}

	public void think() {

	}

	public Action getAction() {
		if (action == Action.waitForInput) {
			return action;
		} else if (action != null && energy >= cost) {
			energy -= cost;
			Action action = this.action;
			this.action = null;
			return action;
		} else {
			return Action.waitForNextAction;
		}
	}

}
