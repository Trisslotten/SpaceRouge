package com.trisse.spacerouge.action;

import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.Area;

public abstract class Action {

	public static final IdleAction waitForNextAction = new IdleAction();
	public static final IdleAction waitForInput = new IdleAction();

	protected Actor actor;

	protected Area area;

	public Action(Actor actor, Area area) {
		this.actor = actor;
		this.area = area;
	}

	public abstract boolean perform();

}
