package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Human extends ActorType {

	public Human(GenericActor generic) {
		super(generic);
	}

	@Override
	public boolean canHandleDoors() {
		return true;
	}
}
