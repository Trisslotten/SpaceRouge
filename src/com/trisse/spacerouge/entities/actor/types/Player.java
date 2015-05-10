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
	}

	public void think() {

	}

}
