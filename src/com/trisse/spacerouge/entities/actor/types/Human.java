package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;

public class Human extends ActorType {

	public Human(GenericActor generic) {
		super(generic);
	}

	@Override
	public boolean canHandleDoors() {
		return true;
	}

	@Override
	public void think(Actor actor) {
		
	}
}
