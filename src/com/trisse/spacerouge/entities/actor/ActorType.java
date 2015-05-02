package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ActorType extends EntityType {

	private Sprite currentSprite;

	public ActorType(String name, int id) {
		super(name, id);
	}

}
