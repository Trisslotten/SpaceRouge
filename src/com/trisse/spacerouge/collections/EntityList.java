package com.trisse.spacerouge.collections;

import java.util.ArrayList;

import com.trisse.spacerouge.entities.Entity;
import com.trisse.spacerouge.entities.EntityType;
import com.trisse.spacerouge.graphics.Sprites;
import com.trisse.spacerouge.util.EntityParser;
import com.trisse.spacerouge.util.Filer;

public class EntityList {

	public ArrayList<EntityType> entityTypes;

	public EntityList(Sprites sprites) {
		String str = Filer.loadString("res/entitites.cfg");
		entityTypes = EntityParser.listFromString(str, sprites);
	}

	public EntityType get(int i) {
		return entityTypes.get(i);
	}
	
	public Entity getEntityInstance(String type, int x, int y) {
		
		return null;
	}

}
