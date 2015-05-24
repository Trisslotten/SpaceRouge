package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.level.Area;

public class Player extends Actor {

	public Player(ActorType type, int x, int y, Area area) {
		super(type, x, y, area);
		isPlayer = true;
		health = 100;
	}

	public void init() {
		isVisible = true;
	}

	public void think() {

	}

}
