package com.trisse.levelEditor.gui.buttons;

import org.lwjgl.input.*;

import com.trisse.levelEditor.*;
import com.trisse.levelEditor.gui.*;
import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class EntityGrid extends Button {

	private int y0 = 3;
	private int x0 = 47;
	private int hoverIndex;
	private EntityType[] entityTypes;

	public EntityGrid(EntityTypePool entityTypePool) {
		width = 16;
		this.entityTypes = entityTypePool.entityTypes();
		y0 = 3;
		x0 = LevelEditor.editorWidth + 1;
	}

	public void clicked(LevelEditor levelEditor) {

	}

	public void render(Screen screen) {
		int y = 0;
		int x = 1;
		for (int i = 0; i < entityTypes.length; i++, x++) {
			screen.draw(entityTypes[i].currentSprite(), x0 + x - 1, y0 + y);
			if (x % width == 0) {
				x = 0;
				y++;
			}
		}
		if (hoverIndex >= 0 && hoverIndex < entityTypes.length)
			screen.draw("hover", x0 + hoverIndex % width, y0 + hoverIndex / width, 2);
	}

	public void handleInput(LevelEditor levelEditor, Input input) {
		int mousex = input.xt() - x0;
		int mousey = input.yt() - y0;
		if (mousex < width && mousex >= 0 && Mouse.isButtonDown(0)) {
			hoverIndex = mousey * width + mousex;
			if (hoverIndex >= 0 && hoverIndex < entityTypes.length)
				levelEditor.selectedEntityType = entityTypes[hoverIndex];
		} else {
			hoverIndex = -1;
		}

	}

}
