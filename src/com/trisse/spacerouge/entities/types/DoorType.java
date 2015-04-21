package com.trisse.spacerouge.entities.types;

import com.trisse.spacerouge.entities.EntityType;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprite;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(double timeScale) {
		// TODO Auto-generated method stub
		
	}

}
