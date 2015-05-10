package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ActorType extends EntityType {

	private Sprite currentSprite;
	
	protected int health;

	public ActorType(String name, int id) {
		super(name, id);
	}

	public abstract boolean canHandleDoors();

}
