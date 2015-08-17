package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.types.*;
import com.trisse.spacerouge.graphics.*;

public abstract class ItemType extends EntityType {
	
	protected String description;

	protected static String CANT_USE = "You cant use this item";

	private int damage;
	
	public String useMessage;

	public ItemType(String name, int id, Sprite sprite, int damage, String useMessage, String desc) {
		super(name, id, sprite);
		this.damage = damage;
		this.description = desc;
	}

	public ItemType(GenericItem generic) {
		super(generic.getName(), generic.getID(), generic.getSprite());
		this.damage = generic.getDamage();
		this.useMessage = generic.getUseMessage();
	}

	public int getDamage() {
		return damage;
	}
	
	public abstract String getStats();

	public abstract boolean use(Actor actor);
	
	public abstract boolean isConsumed();
	
	public String getDescription() {
		return description;
	}

	public String getUseMessage() {
		return useMessage;
	}

	public String getGenericStats() {
		return "Melee damage: " + damage + "\n";
	}

}
