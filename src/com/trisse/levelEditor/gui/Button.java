package com.trisse.levelEditor.gui;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.util.Input;

public class Button {

	public int x, y, width;

	public Button() {

	}

	public void clicked(LevelEditor levelEditor) {
		
	}

	public void render(Screen screen) {

	}

	public void handleInput(LevelEditor levelEditor, Input input) {
		int mousex = input.xt();
		int mousey = input.yt();
		if (input.mousePressed(0) && mousey == y && mousex >= x && mousex < x + width) {
			clicked(levelEditor);
		}
	}
}
