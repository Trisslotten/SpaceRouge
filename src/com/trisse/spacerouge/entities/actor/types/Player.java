package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.Actor;

public class Player extends Actor {

	public Player(int i, int j) {
		x = i;
		y = j;
		isPlayer = true;
	}
	
	public void think() {
		
	}

}
