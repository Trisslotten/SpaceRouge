package com.trisse.spacerouge.action;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public class UseAction extends Action {

	public UseAction(Actor actor, Area area) {
		super(actor, area);
	}

	@Override
	public ActionResult perform(Game game) {
		if (actor.useItem(game.notification))
			return ActionResult.SUCCESS;
		else
			return ActionResult.FAILURE;
	}

}
