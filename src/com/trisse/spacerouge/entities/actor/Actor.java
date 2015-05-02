package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

@SuppressWarnings("serial")
public class Actor extends Entity {

	public Actor(EntityType type, int x, int y) {
		super(type, x, y);
	}

	private Action currentAction;

	private int energy;

	public void setAction(Action action) {
		this.currentAction = action;
	}

	public Action getAction() {
		Action action = currentAction;
		currentAction = null;
		return action;
	}

	public void render(Screen screen, int i, int j) {

	}

}
