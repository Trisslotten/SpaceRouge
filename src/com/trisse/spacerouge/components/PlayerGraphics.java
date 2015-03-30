package com.trisse.spacerouge.components;

import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprite;

public class PlayerGraphics {
	
	private Sprite player = Sprite.player;

	public void update(Player player, Screen screen) {
		screen.draw(this.player, Screen.tileWidth / 2, Screen.tileHeight / 2, 2);
	}
	
	
	

}
