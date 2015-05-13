package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.graphics.*;

public class GenericActor extends ActorType {

	public GenericActor(String name, int id, int team, int corpse, Sprite sprite) {
		super(name, id, team, corpse, sprite);
	}

	@Override
	public boolean canHandleDoors() {
		return false;
	}

	@Override
	public Sprite currentSprite() {
		return null;
	}

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}

	public int getTeam() {
		return team;
	}

	public int getCorpse() {
		return corpse;
	}

	public Sprite getSprite() {
		return sprite;
	}

}
