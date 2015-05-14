package com.trisse.spacerouge.entities.item;

import java.util.*;

import com.trisse.spacerouge.entities.*;
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

	public ItemType get(int i) {
		return itemTypes.get(i);
	}

	public ItemType getType(String type) {
		for (ItemType e : itemTypes) {
			if (e.isType(type)) {
				return e;
			}
		}
		return null;
	}

	public ItemType getType(int id) {
		for (ItemType e : itemTypes) {
			if (e.getID() == id) {
				return e;
			}
		}
		return null;
	}

}
