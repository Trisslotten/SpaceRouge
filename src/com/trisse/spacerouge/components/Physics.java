package com.trisse.spacerouge.components;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.Entity;

public class Physics {

	public void update(Entity entity) {
		entity.move();

		Game.underConstruction(this);

	}

}
