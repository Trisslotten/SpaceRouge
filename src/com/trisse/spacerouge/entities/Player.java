package com.trisse.spacerouge.entities;

import java.util.ArrayList;

import com.trisse.spacerouge.Direction;
import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.components.InputComponent;
import com.trisse.spacerouge.components.Physics;
import com.trisse.spacerouge.components.PlayerGraphics;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.level.Map;

public class Player extends Entity {

	private InputComponent inputComponent = new InputComponent();
	private PlayerGraphics graphics = new PlayerGraphics();

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void handleInput(Game game) {
		inputComponent.handleInput(game, this);
	}

	public void render(Screen screen) {
		graphics.update(this, screen);
	}

	public void tick() {
		physics.update(this);
	}

}
