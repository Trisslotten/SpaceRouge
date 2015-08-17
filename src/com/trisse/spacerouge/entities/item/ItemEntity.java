package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class ItemEntity extends Entity {

	private Item item;

	public ItemEntity(Item item, int x, int y) {
		super();
		this.item = item;
		this.x = x;
		this.y = y;
	}

	public void render(Screen screen, int xpos, int ypos) {
		screen.draw(item.getType().getSprite(), x + xpos, y + ypos, Levels.ITEM);
	}

	public Item getItem() {
		return item;
	}

	public ItemType getType() {
		return item.getType();
	}

	public static ItemEntity newItemEntity(ItemType type, int x, int y) {
		return new ItemEntity(new Item(type), x, y);
	}

}
