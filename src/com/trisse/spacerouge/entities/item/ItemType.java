package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.types.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ItemType extends EntityType {

	protected boolean removeOnUse = false;

	protected static String CANT_USE = "You cant use this item";

	private int damage;
	
	public String useMessage;

	public ItemType(String name, int id, Sprite sprite, int damage, String useMessage) {
		super(name, id, sprite);
		this.damage = damage;
	}

	public ItemType(GenericItem generic) {
		super(generic.getName(), generic.getID(), generic.getSprite());
		this.damage = generic.getDamage();
		this.useMessage = generic.getUseMessage();
	}

	public int getDamage() {
		return damage;
	}
	
	public void performAction() {
		
	}

	public abstract boolean use(Actor actor);

	public String getUseMessage() {
		return useMessage;
	}

}
