
package com.trisse.spacerouge.level;

import java.util.ArrayList;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.entities.StaticEntity;
import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.graphics.Screen;

public class Level {
	
	public ArrayList<StaticEntity> entities = new ArrayList<StaticEntity>();
	
	private Map map;
	
	public Player player;
	
	public Level() {
		
	}
	
	public void init() {
		
		map = new Map();
		player = new Player();
		
	}
	
	public void handleInput(Game game) {
		player.handleInput(game);
	}
	
	public void tick(Game game) {
		double timeScale = 1;
		for (StaticEntity entity : entities) {
			entity.tick(timeScale,entities);
		}
		player.tick(map, entities);
	}
	
	public void render(Screen screen) {
		map.render(screen, player);
		for (StaticEntity e : entities) {
			e.render(screen);
		}
		player.render(screen);
	}
	
}
