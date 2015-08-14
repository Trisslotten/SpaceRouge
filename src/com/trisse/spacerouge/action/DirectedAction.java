package com.trisse.spacerouge.action;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public abstract class DirectedAction extends Action {

	protected Direction dir;

	public DirectedAction(Actor actor, Map map) {
		super(actor, map);
	}

	public DirectedAction(Actor actor, Map map, Direction dir) {
		super(actor, map);
		this.dir = dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

}
