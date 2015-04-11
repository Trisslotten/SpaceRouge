package com.trisse.spacerouge.entities.types;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class Door extends Tile {

	private Sprite open;
	private Sprite closed;

	private boolean isOpen;

	public Door(TileTemplate tileTemplate, int x, int y) {
		super(tileTemplate, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(double timeScale) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sprite defaultSprite() {
		// TODO Auto-generated method stub
		return closed;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4663243014579045912L;

}
