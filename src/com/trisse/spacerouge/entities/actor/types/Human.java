package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Human extends ActorType {

	public Human(String name, Sprite sprite, int id, int team, int corpse) {
		super(name, id, team, corpse, sprite);
	}

	public Human(GenericActor generic) {
		super(generic);
	}

	@Override
	public boolean canHandleDoors() {
		return true;
	}

	@Override
	public Sprite currentSprite() {
		return sprite;
	}
}
