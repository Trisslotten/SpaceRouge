package com.trisse.spacerouge.action;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public abstract class Action {

	protected Actor actor;

	protected Area area;

	public Action(Actor actor, Area area) {
		this.actor = actor;
		this.area = area;
	}

	public abstract ActionResult perform(Game game);

}
