package com.trisse.levelEditor.gui.elements;

import com.trisse.levelEditor.gui.Element;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprite;
import com.trisse.spacerouge.graphics.Sprites;

public class Spacer extends Element {

	public Sprite sprite;

	public Spacer(Sprites sprites) {
		sprite = sprites.getSprite("missing");
	}

	public void render(Screen screen) {
		int x = 45;
		for (int y = 0; y < Screen.tileHeight; y++) {
			screen.draw(sprite, x, y);
		}

	}

}
