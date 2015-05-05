package com.trisse.spacerouge.action;

import com.trisse.spacerouge.entities.actor.Actor;

public abstract class Action {
	
	
	
	protected int cost = 5;

	protected Actor actor;

	public Action(Actor actor) {
		this.actor = actor;
	}
	
	public abstract void perform();
	
	public boolean canPay(int energy) {
		return energy >= cost;
	}

	public int getCost() {
		return cost;
	}

}
