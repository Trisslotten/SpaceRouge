package com.trisse.spacerouge.gameStates;

import java.util.*;

import com.trisse.levelEditor.gui.*;
import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.graphics.*;

public class MainMenuState extends GameState {

	public List<Button> buttons = new ArrayList<Button>();
	public ArrayList<Element> elements = new ArrayList<Element>();

	public MainMenuState(Sprites sprites, EntityTypePool entityTypePool) {
		super(sprites, entityTypePool);

	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub

	}

}
