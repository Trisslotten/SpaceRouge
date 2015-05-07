package com.trisse.spacerouge.action;

import com.trisse.spacerouge.entities.actor.Actor;

public abstract class Action {
	
	protected Actor actor;

	public Action(Actor actor) {
		this.actor = actor;
	}
	
	public abstract void perform();

}
