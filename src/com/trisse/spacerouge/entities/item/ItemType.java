package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.item.types.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ItemType extends EntityType {

	private int damage;

	public ItemType(String name, int id, Sprite sprite, int damage) {
		super(name, id, sprite);
		this.damage = damage;
	}

	public ItemType(GenericItem generic) {
		super(generic.getName(), generic.getID(), generic.getSprite());
		this.damage = generic.getDamage();
	}

	public int getDamage() {
		return damage;
	}

}
