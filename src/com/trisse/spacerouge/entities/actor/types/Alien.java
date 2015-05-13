package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;

public class Alien extends ActorType {
	
	Sprite sprite;
	
	public Alien(String name, Sprite sprite, int id, int team, int corpse) {
		super(name, id, team, corpse);
		this.sprite = sprite;
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
