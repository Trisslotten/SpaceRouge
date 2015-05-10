package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public class AttackAction extends Action {

	private Actor enemy;

	public AttackAction(Actor actor, Area area, Actor enemy) {
		super(actor, area, null);
		this.enemy = enemy;
	}

	@Override
	public ActionResult perform(Game game) {
		actor.attack(enemy);
		
		return ActionResult.SUCCESS;
	}

}
