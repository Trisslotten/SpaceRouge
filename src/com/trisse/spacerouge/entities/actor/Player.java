package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.level.*;

public class Player extends Actor {

	public Player(ActorType type, int x, int y, Area area) {
		super(type, x, y, area);
	}

	public void init() {
		isPlayer = true;
		health = 100;
	}

	public void think() {

	}

}
