package com.trisse.spacerouge.components;

import com.trisse.spacerouge.entities.Entity;

public class Collision {

	public void handle(Entity entity) {
		entity.stopSpeed();
	}

}
