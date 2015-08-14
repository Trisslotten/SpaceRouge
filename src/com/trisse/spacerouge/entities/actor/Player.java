package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.level.*;

public class Player extends Actor {

	public Player(ActorType type, int x, int y, Map map) {
		super(type, x, y, map);
		isPlayer = true;
	}

	public void init() {
		
	}

	public void think() {

	}

}
