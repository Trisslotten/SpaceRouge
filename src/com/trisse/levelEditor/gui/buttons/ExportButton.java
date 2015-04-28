package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;

public class ExportButton extends Button {

	public ExportButton() {
		width = "Export".length();
		x = Screen.tileWidth - 7;
		y = Screen.tileHeight - 2;
	}

	public void clicked(LevelEditor editor) {
		editor.save();
	}

	public void render(Screen screen) {
		screen.drawString("Export", x, y);
	}

}
