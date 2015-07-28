package com.trisse.spacerouge.action;

import com.trisse.spacerouge.Direction;
import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.level.Area;

public class GrabAction extends DirectedAction {

	public GrabAction(Actor actor, Area area) {
		super(actor, area);
		// TODO Auto-generated constructor stub
	}

	public GrabAction(Actor actor, Area area, Direction dir) {
		super(actor, area, dir);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionResult perform(Game game) {
		if(actor.grab(dir)) {
			Item item = actor.getItem();
			return ActionResult.success("You grab the " + item.getType().getName());
		}
		return ActionResult.failure("There is nothing to grab there");
	}

}
