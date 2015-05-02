package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;

public class WalkAction extends Action {

	private Actor actor;

	static Random rand = new Random();

	public WalkAction(Actor actor) {
		this.actor = actor;

	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		Direction dir = null;
		int r = rand.nextInt(4);

		switch (r) {
		case 0:
			dir = Direction.RIGHT;
			break;
		case 1:
			dir = Direction.DOWN;
			break;
		case 2:
			dir = Direction.LEFT;
			break;
		case 3:
			dir = Direction.UP;
			break;
		}
		actor.move(dir);

	}

}
