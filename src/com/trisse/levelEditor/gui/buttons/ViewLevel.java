package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.*;
import com.trisse.levelEditor.gui.*;
import com.trisse.spacerouge.graphics.*;

public class ViewLevel extends Button {

	private int viewLevel;

	public ViewLevel(LevelEditor editor) {
		width = "level".length();
		x = LevelEditor.editorWidth + 1;
		y = Screen.tileHeight - 8;
		viewLevel = editor.viewLevel;
	}

	@Override
	public void clicked(LevelEditor levelEditor) {
		if (++viewLevel > 1) {
			viewLevel = -1;
		}
		levelEditor.viewLevel = viewLevel;
	}

	@Override
	public void render(Screen screen) {
		if (viewLevel < 0) {
			screen.drawString("level all", x, y);
		} else {
			screen.drawString("level " + viewLevel, x, y);
		}

	}
}
