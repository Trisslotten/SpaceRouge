package com.trisse.spacerouge.components;

import org.lwjgl.input.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;

public class InputComponent {

	public void handleInput(Game game, Player player) {
		Input input = game.input;

		if (input.keyDown(Keyboard.KEY_PERIOD)) {
			game.canTick(Keyboard.KEY_PERIOD);
		} else if (input.keyDown(Keyboard.KEY_UP)) {
			game.canTick(Keyboard.KEY_UP);
			player.setSpeed(1, Direction.UP);
		} else if (input.keyDown(Keyboard.KEY_DOWN)) {
			game.canTick(Keyboard.KEY_DOWN);
			player.setSpeed(1, Direction.DOWN);
		} else if (input.keyDown(Keyboard.KEY_LEFT)) {
			game.canTick(Keyboard.KEY_LEFT);
			player.setSpeed(1, Direction.LEFT);
		} else if (input.keyDown(Keyboard.KEY_RIGHT)) {
			game.canTick(Keyboard.KEY_RIGHT);
			player.setSpeed(1, Direction.RIGHT);
		} else {
			player.stopSpeed();
		}

	}
}
