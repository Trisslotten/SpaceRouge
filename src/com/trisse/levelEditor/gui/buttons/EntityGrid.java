package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.*;
import com.trisse.levelEditor.gui.*;
import com.trisse.spacerouge.*;
import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.graphics.*;

public class EntityGrid extends Button {

	private Entities entities;

	private int y0 = 3;
	private int x0 = 47;
	private int hoverIndex;

	public EntityGrid(Entities entities) {
		this.entities = entities;
		width = 10;
	}

	public void clicked(LevelEditor levelEditor) {

	}

	public void render(Screen screen) {

		int y = 0;
		int x = 0;
		for (int i = 0; i < entities.length(); i++) {
			screen.draw(entities.get(i).sprite, x + x0, y + y0, 0);
			if (hoverIndex >= 0 && hoverIndex < entities.length() && hoverIndex == i) {
				screen.draw("hover", x + x0, y + y0, 1);
			}

			if (++x >= width) {
				x = 0;
				y++;
			}
		}
	}

	public void handleInput(LevelEditor levelEditor, Input input) {
		int mousex = input.xt() - x0;
		int mousey = input.yt() - y0;
		if (mousex < width && mousex >= 0) {
			hoverIndex = mousey * width + mousex;
		} else {
			hoverIndex = -1;
		}
		if (hoverIndex >= 0 && hoverIndex < entities.length() && input.mousePressed(0)) {
			levelEditor.selectedEntity = entities.get(hoverIndex);
		}

	}

}