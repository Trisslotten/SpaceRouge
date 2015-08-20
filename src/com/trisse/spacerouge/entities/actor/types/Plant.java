package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Plant extends ActorType {
	
	private double growthRate = 1;

	public Plant(GenericActor generic) {
		super(generic);
		
	}

	@Override
	public boolean canHandleDoors() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void think(Actor actor) {
		
	}

}
