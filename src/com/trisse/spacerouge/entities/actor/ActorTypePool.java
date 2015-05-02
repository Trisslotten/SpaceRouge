package com.trisse.spacerouge.entities.actor;

import java.util.*;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class ActorTypePool {

	public ArrayList<ActorType> actorTypes;

	public EntityType[] entityTypes() {
		return (EntityType[]) actorTypes.toArray(new EntityType[actorTypes.size()]);
	}

	public ActorTypePool(Sprites sprites) {
		String str = Filer.loadString("res/actors.cfg");

		actorTypes = EntityParser.actorList(sprites);

	}

	public EntityType get(int i) {
		return actorTypes.get(i);
	}

	/*
	 * public Entity getEntityInstance(String type, int x, int y) { return new
	 * Entity(getType(type), x, y); }
	 */

	private EntityType getType(String type) {
		for (EntityType e : actorTypes) {
			if (e.isType(type)) {
				return e;
			}
		}
		return null;
	}

}
