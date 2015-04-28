package com.trisse.levelEditor.gui.elements;

import com.trisse.levelEditor.gui.Element;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprite;
import com.trisse.spacerouge.graphics.Sprites;

public class Grid extends Element {
	
	private Sprite sprite;
	
	public Grid(Sprites sprites) {
		this.sprite = sprites.getSprite("grid");
	}

	@Override
	public void render(Screen screen) {
		
		for(int y=0;y<Screen.tileHeight;y++) {
			for(int x=0;x<46;x++) {
				screen.draw(sprite, x, y,2);
			}
		}
	}

}
