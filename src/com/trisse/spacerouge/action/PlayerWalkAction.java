package com.trisse.spacerouge.action;

import org.lwjgl.input.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;

public class PlayerWalkAction extends Action {

	public PlayerWalkAction(Actor actor) {
		super(actor);
	}

	
	
	@Override
	public void perform() {
		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
			actor.move(Direction.UP);
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			actor.move(Direction.DOWN);
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			actor.move(Direction.LEFT);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			actor.move(Direction.RIGHT);
	}

}
