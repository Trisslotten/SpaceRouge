package com.trisse.spacerouge.components;

import java.util.ArrayList;

import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.entities.StaticEntity;
import com.trisse.spacerouge.level.Map;

public class PlayerPhysicsComponent extends PhysicsComponent {

	public void update(Player player, Map map, ArrayList<StaticEntity> entities) {
		player.move();
	}

}
