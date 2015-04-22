package com.trisse.spacerouge.entities.types;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class DoorType extends EntityType {

	private Sprite open;
	private Sprite closed;

	public DoorType(String name, Sprite open, Sprite closed) {
		super(name);
		this.open = open;
		this.closed = closed;
		heightLevel = 1;
	}

	@Override
	public void render(Screen screen) {
		throw Game.notImplemented();
		
	}

	@Override
	public void tick(double timeScale) {
		throw Game.notImplemented();
	}

	@Override
	public Sprite currentSprite() {
		return closed;
	}

}