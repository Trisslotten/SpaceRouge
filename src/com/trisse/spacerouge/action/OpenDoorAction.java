package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.level.*;

public class OpenDoorAction extends DirectedAction {

	private Tile tile;

	public OpenDoorAction(Actor actor, Area area, Tile tile) {
		super(actor, area);
		this.tile = tile;
	}

	public OpenDoorAction(Actor actor, Area area) {
		super(actor, area);
	}

	public ActionResult perform(Game game) {
		if (tile == null) {
			int x = actor.x() + dir.xspd();
			int y = actor.y() + dir.yspd();
			ArrayList<Tile> tiles = area.getTilesOn(x, y);
			if (!tiles.isEmpty())
				for (Tile t : tiles)
					if (t.getType().opensTo >= 1) {
						tile = t;
						break;
					}
		}
		if (!actor.getType().canHandleDoors() || tile == null)
			return ActionResult.FAILURE;
		tile.open(game.tilePool);
		return ActionResult.success("You open the " + tile.getType().getName());
	}

}
