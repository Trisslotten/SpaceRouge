package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ActorType extends EntityType {

	protected int health;
	
	protected int damage;

	protected int team;

	protected int corpse;

	protected Sprite sprite;

	public ActorType(String name, int id, int team, int corpse, Sprite sprite) {
		super(name, id);
		this.team = team;
		this.corpse = corpse;
		this.sprite = sprite;
	}

	public ActorType(GenericActor generic) {
		super(generic.getName(), generic.getID());
		this.team = generic.getTeam();
		this.corpse = generic.getCorpse();
		this.sprite = generic.getSprite();
	}

	public abstract boolean canHandleDoors();

	public int team() {
		return team;
	}

}
