package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.*;

@SuppressWarnings("serial")
public class Actor extends Entity {

	private Action currentAction;

	private int energy;
	
	

	public Actor(EntityType type) {
		super(type);
	}

	public void setAction(Action action) {
		this.currentAction = action;
	}

	public Action getAction() {
		Action action = currentAction;
		currentAction = null;
		return action;
	}

}
