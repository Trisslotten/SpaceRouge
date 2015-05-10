package com.trisse.spacerouge.action;

import java.util.Random;

import com.trisse.spacerouge.Direction;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.Area;

public class WalkAction extends Action {

	private Direction dir;

	public WalkAction(Actor actor, Area area, Direction dir) {
		super(actor, area);
		this.dir = dir;
	}

	@Override
	public ActionResult perform() {
		int x = actor.x() + dir.xspd();
		int y = actor.y() + dir.yspd();
		// boolean canMove = true;
		if (!area.isPassable(x, y)) {
			return ActionResult.FAILURE;
		}
		actor.move(dir);
		return ActionResult.SUCCESS;
	}

	@Override
	public String toString() {
		return "WalkAction";
	}
}
