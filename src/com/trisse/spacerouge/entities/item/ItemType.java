package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class ItemType extends EntityType {

	public ItemType(String name, int id) {
		super(name, id);
	}

	@Override
	public Sprite currentSprite() {
		return null;
	}

}
