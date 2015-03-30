
package com.trisse.spacerouge.components;

import org.lwjgl.input.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;

public class InputComponent {
	
	public void handleInput(Game game, Player player) {
		Input input = game.input;
		
		if (input.down(Keyboard.KEY_PERIOD)) {
			game.canTick(Keyboard.KEY_PERIOD);
		}
		if (input.down(Keyboard.KEY_UP)) {
			game.canTick(Keyboard.KEY_UP);
			player.setSpeed(Direction.UP);
		}
		if (input.down(Keyboard.KEY_DOWN)) {
			game.canTick(Keyboard.KEY_DOWN);
			player.setSpeed(Direction.DOWN);
		}
		if (input.down(Keyboard.KEY_LEFT)) {
			game.canTick(Keyboard.KEY_LEFT);
			player.setSpeed(Direction.LEFT);
		}
		if (input.down(Keyboard.KEY_RIGHT)) {
			game.canTick(Keyboard.KEY_RIGHT);
			player.setSpeed(Direction.RIGHT);
		}
		
	}
}
