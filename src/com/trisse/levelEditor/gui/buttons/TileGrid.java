package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.collections.EntityTypePool;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.util.Input;

public class TileGrid extends Button {

	private EntityTypePool entityList; 

	private int y0 = 3;
	private int x0 = 47;
	private int hoverIndex;

	public TileGrid(EntityTypePool list) {
		this.entityList = list;
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
