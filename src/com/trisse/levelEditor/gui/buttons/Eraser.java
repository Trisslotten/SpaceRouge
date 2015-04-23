package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;

public class Eraser extends Button {

	public Eraser() {
		x = 47;
		y = 30;
		width = 5;
	}

	public void render(Screen screen) {
		screen.drawString("erase", x, y);
	}

	public void clicked(LevelEditor levelEditor) {
		levelEditor.selectedEntityType = null;
	}

}
