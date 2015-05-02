package com.trisse.spacerouge.entities.item;

import java.util.*;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class ItemTypePool {

	public ArrayList<ItemType> itemTypes;

	public EntityType[] entityTypes() {
		return (EntityType[]) itemTypes.toArray(new EntityType[itemTypes.size()]);
	}

	public ItemTypePool(Sprites sprites) {
		itemTypes = EntityParser.itemList(sprites);
	}

	public EntityType get(int i) {
		return itemTypes.get(i);
	}

	/*
	 * public Entity getEntityInstance(String type, int x, int y) { return new
	 * Entity(getType(type), x, y); }
	 */

	private EntityType getType(String type) {
		for (EntityType e : itemTypes) {
			if (e.isType(type)) {
				return e;
			}
		}
		return null;
	}

}
