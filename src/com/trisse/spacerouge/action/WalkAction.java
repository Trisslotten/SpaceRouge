package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;

public class WalkAction extends Action {

	static Random rand = new Random();
	
	private Direction dir;

	public WalkAction(Actor actor, Direction dir) {
		super(actor);
		this.dir = dir;
	}

	@Override
	public void perform() {
		actor.move(dir);

	}

}
