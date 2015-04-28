package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;

public class LoadButton extends Button {

	public LoadButton() {
		width = "Load".length();
		x = Screen.tileWidth - 7;
		y = Screen.tileHeight - 4;
	}

	@Override
	public void clicked(LevelEditor levelEditor) {
		levelEditor.load();

	}

	@Override
	public void render(Screen screen) {
		screen.drawString("Load", x, y);
	}

}
