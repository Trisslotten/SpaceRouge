package com.trisse.spacerouge.entities.item;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class ItemEntity extends Entity {

	private Item item;

	public ItemEntity(Item item, int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.item = item;
		isTransparent = true;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		screen.draw(item.getType().getSprite(), x - xoffset, y - yoffset, Levels.ITEM);
	}

	public Item getItem() {
		return item;
	}

}
