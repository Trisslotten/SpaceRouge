package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ActorType extends EntityType {

	protected int health;

	protected int damage;

	protected int team;

	protected int corpse;

	public ActorType(String name, int id, int team, int corpse, Sprite sprite, int damage, int health) {
		super(name, id, sprite);
		this.team = team;
		this.corpse = corpse;
		this.damage = damage;
		this.health = health;
	}

	public ActorType(GenericActor generic) {
		super(generic.getName(), generic.getID(), generic.getSprite());
		this.team = generic.getTeam();
		this.corpse = generic.getCorpse();
		this.damage = generic.getDamage();
		this.health = generic.getHealth();
	}

	public abstract boolean canHandleDoors();

	public abstract void think(Actor actor);

	public int team() {
		return team;
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

}
