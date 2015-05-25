package com.trisse.spacerouge.gui;

import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.entities.item.Item;
import com.trisse.spacerouge.graphics.Screen;

public class ItemViewer extends Element {
	
	private Actor player;
	
	public ItemViewer(Actor actor) {
		this.player = actor;
	}

	@Override
	public void render(Screen screen) {
		Item item = player.getItem();
		screen.drawString("Hands:", 1, 3);
		if(item==null) {
			screen.drawString("Nothing", 7, 3);
		} else {
			screen.drawString(item.getType().getName(), 9, 3);
			screen.draw(item.getType().getSprite(), 7, 3);
		}
	}

}
