package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.types.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public abstract class ItemType extends EntityType {

	protected boolean removeOnUse = false;

	protected static String CANT_USE = "You cant use this item";

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

	public abstract boolean use(Actor actor, StringContainer message);

}
