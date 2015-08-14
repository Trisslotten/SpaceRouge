package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class ItemEntity {

	private Item item;

	public ItemEntity(Item item) {
		super();
		this.item = item;
	}

	public void render(Screen screen, int x, int y) {
		screen.draw(item.getType().getSprite(), x, y, Levels.ITEM);
	}

	public Item getItem() {
		return item;
	}
	
	public ItemType getType() {
		return item.getType();
	}

	public static ItemEntity newItemEntity(ItemType type) {
		return new ItemEntity(new Item(type));
	}

}
