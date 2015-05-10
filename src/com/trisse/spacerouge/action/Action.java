package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public abstract class Action {

	protected Actor actor;
	
	protected ArrayList<Actor> actors;
	
	protected Area area;

	public Action(Actor actor, Area area, ArrayList<Actor> actors) {
		this.actor = actor;
		this.area = area;
		this.actors = actors;
	}

	public abstract ActionResult perform(Game game);

}
