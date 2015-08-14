package com.trisse.spacerouge.action;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public class UseAction extends Action {

	public UseAction(Actor actor, Map map) {
		super(actor, map);
	}

	@Override
	public ActionResult perform(Game game) {
		if (actor.activateItem())
			return ActionResult.success(actor.getItem().getUseMessage());
		else
			return ActionResult.FAILURE;
	}

}
