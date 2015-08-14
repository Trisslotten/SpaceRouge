package com.trisse.spacerouge.action;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public class AttackAction extends DirectedAction {

	private Actor enemy;

	public AttackAction(Actor actor, Map map, Actor enemy) {
		super(actor, map);
		this.enemy = enemy;
	}

	@Override
	public ActionResult perform(Game game) {
		actor.attack(enemy);
		
		return ActionResult.success("You attack the " + enemy.getType().getName());
	}
}
