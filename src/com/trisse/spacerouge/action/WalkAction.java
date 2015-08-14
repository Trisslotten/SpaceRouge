package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.level.Map;

public class WalkAction extends Action {

	private Direction dir;

	public WalkAction(Actor actor, Map map, Direction dir) {
		super(actor, map);
		this.dir = dir;
	}

	@Override
	public ActionResult perform(Game game) {
		int x = actor.x() + dir.xspd();
		int y = actor.y() + dir.yspd();
		boolean success = true;

		// check for collision with walls
		// if tile is a door open it
		ArrayList<Tile> tiles = map.getTiles(x, y);
		if (!tiles.isEmpty()) {
			for (Tile t : tiles) {
				if (t.getType().opensTo >= 1) {
					return new ActionResult(new OpenDoorAction(actor, map, t));
				}
				if (!t.isPassable()) {
					success = false;
				}
			}
		}
		// check if walking in to actor and choose to attack
		// TODO let player choose who to attack
		ArrayList<Actor> actors = map.getActors(x, y);
		if (!actors.isEmpty())
			for (Actor a : actors)
				if (!a.isSameTeam(actor))
					return new ActionResult(new AttackAction(actor, map, actors.get(0)));
				else if (actors.get(0) != actor)
					success = false;
		if (!success) {
			return ActionResult.FAILURE;
		}
		actor.move(dir);
		return ActionResult.SUCCESS;
	}
}
