package com.trisse.spacerouge.action;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.level.*;

public class UseItemAction extends Action {

	public UseItemAction(Actor actor, Map map) {
		super(actor, map);
	}

	@Override
	public ActionResult perform(Game game) {
		Item item = actor.getItem();
		if(actor.activateItem()) {
			return ActionResult.success(item.getUseMessage());
		} else {
			return ActionResult.failure(item.getUseMessage());
		}
	}

}
