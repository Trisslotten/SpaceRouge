package com.trisse.spacerouge.entities.types;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class DoorType extends EntityType {

	private Sprite open;
	private Sprite closed;

	public DoorType(String name, Sprite open, Sprite closed) {
		super(name);
		this.open = open;
		this.closed = closed;
	}

}
