package com.trisse.spacerouge.components;

import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprite;

public class PlayerGraphics {

	private Sprite currentSprite;

	public PlayerGraphics(Sprite sprite) {
		this.currentSprite = sprite;
	}

	public void update(Player player, Screen screen) {
		screen.draw(currentSprite, Screen.tileWidth / 2, Screen.tileHeight / 2, 2);
	}

}
