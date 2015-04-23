package com.trisse.spacerouge.collections;

import java.util.*;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class EntityTypePool {

	public ArrayList<EntityType> entityTypes;

	public EntityType[] entityTypes() {
		return (EntityType[]) entityTypes.toArray(new EntityType[entityTypes.size()]);
	}

	public EntityTypePool(Sprites sprites) {
		String str = Filer.loadString("res/entities.cfg");

		entityTypes = EntityParser.listFromString(str, sprites);
	}

	public EntityType get(int i) {
		return entityTypes.get(i);
	}

	public Entity getEntityInstance(String type, int x, int y) {
		return new Entity(getType(type), x, y);
	}

	private EntityType getType(String type) {
		for (EntityType e : entityTypes) {
			if (e.isType(type)) {
				return e;
			}
		}
		return null;
	}

}
