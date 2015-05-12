package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ItemType extends EntityType {

	public ItemType(String name, int id) {
		super(name, id);
	}
	
	public abstract Sprite currentSprite();

}
