package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.components.InputComponent;
import com.trisse.spacerouge.components.PlayerGraphics;
import com.trisse.spacerouge.graphics.Screen;

public class Player extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398803915941718036L;
	
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
