package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.components.InputComponent;
import com.trisse.spacerouge.components.PlayerGraphics;
import com.trisse.spacerouge.graphics.*;

public class Player extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398803915941718036L;

	private InputComponent inputComponent = new InputComponent();
	private PlayerGraphics graphics;

	public Player(int x, int y, Sprites sprites) {
		graphics = new PlayerGraphics(sprites.getSprite("player"));
		this.x = x;
		this.y = y;
	}

	public void handleInput(Game game) {
		inputComponent.handleInput(game, this);
	}

	public void render(Screen screen) {
		graphics.update(this, screen);
	}

	@Override
	public void tick(double timeScale) {
		physics.update(this);

	}

	@Override
	public Sprite defaultSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
