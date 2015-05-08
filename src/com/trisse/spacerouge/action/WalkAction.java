package com.trisse.spacerouge.action;

import java.util.Random;

import com.trisse.spacerouge.Direction;
import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.level.Area;

public class WalkAction extends Action {

	static Random rand = new Random();

	private Direction dir;

	public WalkAction(Actor actor, Area area, Direction dir) {
		super(actor, area);
		this.dir = dir;
	}

	@Override
	public void perform() {
		int x = actor.x() + dir.xspd();
		int y = actor.y() + dir.yspd();
		boolean canMoveThroughTile =!area.canMoveThrough(x, y); 
		
	}

}
