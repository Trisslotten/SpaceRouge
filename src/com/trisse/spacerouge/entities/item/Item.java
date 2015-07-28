package com.trisse.spacerouge.entities.item;

public class Item {

	private ItemType type;

	public Item(ItemType type) {
		this.type = type;
	}

	public ItemType getType() {
		return type;
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
