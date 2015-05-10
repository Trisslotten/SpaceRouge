package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.level.*;

public class OpenDoorAction extends Action {

	private Direction dir;

	private Tile tile;

	public OpenDoorAction(Actor actor, Area area, Direction dir, Tile tile) {
		super(actor, area);
		this.dir = dir;
		this.tile = tile;
	}

	@Override
	public ActionResult perform(Game game) {
		TileType type = game.tilePool.getType((tile.getType().opensTo));
		tile.setType(type);

		return ActionResult.SUCCESS;
	}

}
