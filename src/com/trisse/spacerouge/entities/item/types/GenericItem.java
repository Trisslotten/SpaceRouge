package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.item.ItemType;
import com.trisse.spacerouge.graphics.Sprite;

public class GenericItem extends ItemType {

	protected Sprite sprite;

	public GenericItem(String name, int id, Sprite sprite, int damage) {
		super(name, id, sprite, damage);
	}

}
