package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;

public class ExportButton extends Button {
	
	public ExportButton() {
		width = "Export".length();
		x = 57;
		y = 34;
	}
	
	public void clicked(LevelEditor editor) {
		editor.save();
	}
	
	public void render(Screen screen) {
		screen.drawString("Export", x, y);
	}

}
