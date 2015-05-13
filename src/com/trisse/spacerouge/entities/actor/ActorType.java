package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ActorType extends EntityType {

	protected int health;
	protected int damage;

	protected int team;
	
	protected int corpse;

	public ActorType(String name, int id, int team, int corpse) {
		super(name, id);
		this.team = team;
		this.corpse = corpse;
	}

	public abstract boolean canHandleDoors();

	public int team() {
		return team;
	}

}
