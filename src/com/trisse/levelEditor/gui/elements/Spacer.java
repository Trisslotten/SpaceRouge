package com.trisse.levelEditor.gui.elements;

import com.trisse.levelEditor.gui.Element;
import com.trisse.spacerouge.graphics.Screen;

public class Spacer extends Element {

	public void render(Screen screen) {
		int x = 45;
		for (int y = 0; y < Screen.tileHeight; y++) {
			// screen.draw(Sprite.missing, x, y);
		}

	}

}
