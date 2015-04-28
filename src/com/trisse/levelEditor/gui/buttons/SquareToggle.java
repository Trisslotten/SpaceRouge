package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprites;

public class SquareToggle extends Button {
	
	
	private boolean squareTool = false;

	public SquareToggle() {
		x = 47;
		y = 32;
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
