package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.item.ItemType;
import com.trisse.spacerouge.graphics.Sprite;

public class Corpse extends ItemType {
	
	protected Sprite sprite;

	public Corpse(String name, int id, Sprite sprite) {
		super(name, id);
		this.sprite = sprite;
	}

	@Override
	public Sprite currentSprite() {
		return sprite;
	}

}
