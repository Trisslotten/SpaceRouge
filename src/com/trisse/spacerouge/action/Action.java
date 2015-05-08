package com.trisse.spacerouge.action;

import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.Area;

public abstract class Action {
	
	protected Actor actor;  
	
	protected Area area;

	public Action(Actor actor,Area area) {
		this.actor = actor;
		this.area = area;
	}
	
	public abstract void perform();

}
