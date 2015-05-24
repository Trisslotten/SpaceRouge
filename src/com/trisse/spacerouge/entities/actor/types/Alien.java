package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Alien extends ActorType {

	public Alien(GenericActor gnrc) {
		super(gnrc);
	}

	@Override
	public boolean canHandleDoors() {
		// TODO Auto-generated method stub
		return false;
	}

}
