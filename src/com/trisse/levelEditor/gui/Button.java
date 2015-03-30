package com.trisse.levelEditor.gui;

import org.lwjgl.input.Mouse;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.spacerouge.Input;
import com.trisse.spacerouge.graphics.Screen;

public class Button {

	public int x, y;

	public Button() {

	}

	public void clicked(LevelEditor levelEditor) {

	}

	public void render(Screen screen) {

	}

	public void handleInput(LevelEditor levelEditor, Input input) {
		int mousex = Mouse.getX() / Screen.tileSize;
		int mousey = Mouse.getY() / Screen.tileSize;
		if (input.mousePressed(0) && mousex == x && mousey == y) {
			clicked(levelEditor);
		}
	}
}
