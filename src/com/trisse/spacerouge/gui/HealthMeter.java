package com.trisse.spacerouge.gui;

import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.graphics.Screen;

public class HealthMeter extends Element {
	
	private Actor player;

	public HealthMeter(Actor player) {
		this.player = player;
	}

	@Override
	public void render(Screen screen) {
		screen.drawString(("HP " + player.getHealth()), 1, 1, 9);
	}

}
