package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.actor.*;

public class Item {

	private ItemType type;

	public Item(ItemType type) {
		this.type = type;
	}

	public ItemType getType() {
		return type;
	}
	
	public boolean activate(Actor actor) {
		return type.use(actor);
	}

	public boolean getsUsed() {
		return type.removeOnUse;
	}
	
	public boolean performAction() {
		type.performAction();
		return false;
	}

	public String getUseMessage() {
		return type.getUseMessage();
	}

}
