package com.trisse.spacerouge.action;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.level.*;

public class DropAction extends Action {

	public DropAction(Actor actor, Map map) {
		super(actor, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionResult perform(Game game) {
		Item item = actor.getItem();
		if (actor.somethingInHands()) {
			actor.dropInHands();
			return ActionResult.success("You drop the " + item.getType().getName());
		} else {
			return ActionResult.failure("You have nothing to drop");
		}
	}

}
