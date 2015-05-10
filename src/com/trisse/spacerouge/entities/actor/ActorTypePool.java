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
		actorTypes = EntityParser.actorList(sprites);

	}

	public ActorType get(int i) {
		return actorTypes.get(i);
	}

	public ActorType getType(String type) {
		for (ActorType e : actorTypes) {
			if (e.isType(type)) {
				return e;
			}
		}
		return null;
	}

}
