package com.trisse.spacerouge.action;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.level.*;

public abstract class DirectedAction extends Action {

	protected Direction dir;

	public DirectedAction(Actor actor, Area area) {
		super(actor, area);
	}

	public DirectedAction(Actor actor, Area area, Direction dir) {
		super(actor, area);
		this.dir = dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

}
