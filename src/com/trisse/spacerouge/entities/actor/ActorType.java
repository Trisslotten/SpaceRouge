package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ActorType extends EntityType {

	protected int health;
	protected int damage;

	protected int team;

	public ActorType(String name, int id, int team) {
		super(name, id);
		this.team = team;
	}

	public abstract boolean canHandleDoors();

	public int team() {
		return team;
	}

}
