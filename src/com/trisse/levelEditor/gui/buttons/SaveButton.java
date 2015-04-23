package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;

public class SaveButton extends Button {

	public SaveButton() {
		width = 4;

		x = 57;
		y = 32;
	}

	public void render(Screen screen) {
		screen.drawString("save", x, y);
	}

	public void clicked(LevelEditor levelEditor) {
		levelEditor.save();
	}

}
