package com.trisse.spacerouge.components;

import java.util.ArrayList;

import com.trisse.spacerouge.entities.Entity;
import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.level.Map;

public class Physics {

	public void update(Entity entity) {
		entity.move();
	}

}
