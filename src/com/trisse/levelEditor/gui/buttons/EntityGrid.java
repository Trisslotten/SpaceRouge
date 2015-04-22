package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.*;
import com.trisse.levelEditor.gui.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class EntityGrid extends Button {


	private int y0 = 3;
	private int x0 = 47;
	private int hoverIndex;

	public EntityGrid() {
		width = 10;
	}

	public void clicked(LevelEditor levelEditor) {

	}

	public void render(Screen screen) {
		
	}

	public void handleInput(LevelEditor levelEditor, Input input) {
		int mousex = input.xt() - x0;
		int mousey = input.yt() - y0;
		if (mousex < width && mousex >= 0) {
			hoverIndex = mousey * width + mousex;
		} else {
			hoverIndex = -1;
		}

	}

}
