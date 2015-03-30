package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.Input;
import com.trisse.spacerouge.graphics.Screen;

public class SaveButton extends Button {

	public SaveButton() {
		x = 0;
		y = 0;
	}

	public void render(Screen screen) {
		screen.drawString("save", x, y);
	}
	
	public void clicked(LevelEditor levelEditor) {
		
	}

}
