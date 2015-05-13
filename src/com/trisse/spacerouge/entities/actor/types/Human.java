package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Human extends ActorType {

	Sprite sprite;

	public Human(String name, Sprite sprite2, int id, int team, int corpse) {
		super(name, id, team, corpse);
		sprite = sprite2;
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
