package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Alien extends ActorType {

	public Alien(String name, Sprite sprite, int id, int team, int corpse) {
		super(name, id, team, corpse, sprite);
	}

	public Alien(GenericActor gnrc) {
		super(gnrc.getName(), gnrc.getID(), gnrc.getTeam(), gnrc.getCorpse(), gnrc.getSprite());
	}

	@Override
	public boolean canHandleDoors() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sprite currentSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
