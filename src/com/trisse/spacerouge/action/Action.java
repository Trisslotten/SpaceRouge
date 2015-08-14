package com.trisse.spacerouge.action;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.*;

public abstract class Action {

	protected Actor actor;
	
	protected Map map;

	public Action(Actor actor, Map map) {
		this.actor = actor;
		this.map = map;
	}

	public abstract ActionResult perform(Game game);

}
