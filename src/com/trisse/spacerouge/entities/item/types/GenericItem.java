package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.item.ItemType;
import com.trisse.spacerouge.graphics.Sprite;

public class GenericItem extends ItemType {
	
	protected Sprite sprite;

	public GenericItem(String name, int id, Sprite sprite) {
		super(name, id);
	}

	@Override
	public Sprite currentSprite() {
		return sprite;
	}

}
