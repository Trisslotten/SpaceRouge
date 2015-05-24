package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;

public class Alien extends ActorType {

	public Alien(GenericActor gnrc) {
		super(gnrc);
	}

	@Override
	public boolean canHandleDoors() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void think(Actor actor) {
		switch (Game.rand.nextInt(4)) {
		case 0:
			actor.walk(Direction.UP);
			break;
		case 1:
			actor.walk(Direction.DOWN);
			break;
		case 2:
			actor.walk(Direction.LEFT);
			break;
		case 3:
			actor.walk(Direction.RIGHT);
			break;
		default:
			actor.walk(Direction.NONE);
		}
	}

}
