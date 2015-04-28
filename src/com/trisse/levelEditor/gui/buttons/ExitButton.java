package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;

public class ExitButton extends Button {

	public ExitButton() {
		x = LevelEditor.editorWidth + 1;
		y = Screen.tileHeight - 2;
		width = 4;
	}

	@Override
	public void clicked(LevelEditor levelEditor) {
		levelEditor.stop();
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		screen.drawString("Exit", x, y);
	}

}
