package com.trisse.spacerouge.action;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.Area;

public class DropAction extends Action {

	public DropAction(Actor actor, Area area) {
		super(actor, area);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionResult perform(Game game) {
		if (actor.somethingInHands()) {
			actor.dropInHands();
			return ActionResult.SUCCESS;
		} else {
			return ActionResult.FAILURE;
		}
	}

}
