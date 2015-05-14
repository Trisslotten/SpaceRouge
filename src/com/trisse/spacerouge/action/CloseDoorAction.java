package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.level.*;

public class CloseDoorAction extends DirectedAction {

	public CloseDoorAction(Actor actor, Area area) {
		super(actor, area);
	}

	@Override
	public ActionResult perform(Game game) {
		int x = actor.x() + dir.xspd();
		int y = actor.y() + dir.yspd();
		Tile tile = null;
		ArrayList<Tile> tiles = area.getTilesOn(x, y);
		if (!tiles.isEmpty()) {
			for (Tile t : tiles) {
				if (t.getType().closesTo >= 1) {
					tile = t;
					break;
				}
			}
		}
		if (!actor.getType().canHandleDoors() || tile == null)
			return ActionResult.FAILURE;

		tile.close(game.tilePool);
		return ActionResult.SUCCESS;
	}

}
