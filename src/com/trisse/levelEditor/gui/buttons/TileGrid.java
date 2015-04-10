package com.trisse.levelEditor.gui.buttons;

import com.trisse.levelEditor.LevelEditor;
import com.trisse.levelEditor.gui.Button;
import com.trisse.spacerouge.Input;
import com.trisse.spacerouge.collections.Tiles;
import com.trisse.spacerouge.graphics.Screen;

public class TileGrid extends Button {

	private Tiles tiles;

	private int y0 = 3;
	private int x0 = 47;
	private int hoverIndex;

	public TileGrid(Tiles tiles) {
		this.tiles = tiles;
		width = 10;
	}

	public void clicked(LevelEditor levelEditor) {

	}

	public void render(Screen screen) {

		int y = 0;
		int x = 0;
		for (int i = 0; i < tiles.length(); i++) {
			screen.draw(tiles.get(i).sprite, x + x0, y + y0, 0);
			if (hoverIndex >= 0 && hoverIndex < tiles.length() && hoverIndex == i) {
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
		if (hoverIndex >= 0 && hoverIndex < tiles.length() && input.mousePressed(0)) {
			levelEditor.selectedTile = tiles.get(hoverIndex);
		}

	}

}
