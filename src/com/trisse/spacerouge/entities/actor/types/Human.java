package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Human extends ActorType {
	
	Sprite sprite;

	public Human(String name, Sprite sprite2, int id) {
		super(name, id);
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
