package com.trisse.spacerouge.action;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.Area;

public class AttackAction extends DirectedAction {

	private Actor enemy;

	public AttackAction(Actor actor, Area area, Actor enemy) {
		super(actor, area);
		this.enemy = enemy;
	}

	@Override
	public ActionResult perform(Game game) {
		actor.attack(enemy);
		
		return ActionResult.success("You attack the " + enemy.getType().getName());
	}
}
