
package com.trisse.spacerouge.entities;

import java.util.ArrayList;

import com.trisse.spacerouge.Direction;
import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.components.InputComponent;
import com.trisse.spacerouge.components.PlayerGraphicsComponent;
import com.trisse.spacerouge.components.PlayerPhysicsComponent;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.level.Map;

public class Player extends MovingEntity {
	
	private InputComponent inputComponent = new InputComponent();
	private PlayerGraphicsComponent graphicsComponent = new PlayerGraphicsComponent();
	private PlayerPhysicsComponent physicsComponent = new PlayerPhysicsComponent();
	
	public void handleInput(Game game) {
		inputComponent.handleInput(game, this);
	}
	
	public void render(Screen screen) {
		graphicsComponent.update(this, screen);
	}
	
	public void tick(Map map, ArrayList<StaticEntity> entities) {
		physicsComponent.update(this, map, entities);
	}
	
	public void move(Direction dir) {
		
		switch (dir) {
		case UP:
			
			break;
		case DOWN:
			
			break;
		case LEFT:
			
			break;
		case RIGHT:
			
			break;
		default:
			
		}
	}
	
}
