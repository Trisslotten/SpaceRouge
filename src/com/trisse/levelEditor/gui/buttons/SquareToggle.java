package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.*;
import com.trisse.levelEditor.gui.*;
import com.trisse.spacerouge.graphics.*;

public class SquareToggle extends Button {
	
	
	private boolean squareTool = false;

	public SquareToggle() {
		x = LevelEditor.editorWidth + 1;
		y = Screen.tileHeight - 4;
		width = "Square".length();
	}
	
	@Override
	public void clicked(LevelEditor levelEditor) {
		levelEditor.squareTool = !levelEditor.squareTool;
		squareTool = levelEditor.squareTool;
	}

	@Override
	public void render(Screen screen) {
		screen.drawString("Square", x, y);
		if(squareTool) {
			screen.draw("checked", x+width, y);
		} else {
			screen.draw("notchecked", x+width, y);
		}
	}

}
