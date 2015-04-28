package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;

public class Eraser extends Button {

	public Eraser() {
		x = LevelEditor.editorWidth + 1;
		y = Screen.tileHeight - 6;
		width = 5;
	}

	public void render(Screen screen) {
		screen.drawString("erase", x, y);
//		screen.draw
	}

	public void clicked(LevelEditor levelEditor) {
		if (levelEditor.selectedEntityType == null) {
			int level = levelEditor.level;
			if (level == 0) {
				level = 1;
			} else {
				level = 0;
			}
			levelEditor.level = level;
		} else {
			levelEditor.selectedEntityType = null;
		}
	}

}
