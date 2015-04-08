package com.trisse.spacerouge.level;

import java.io.Serializable;
import java.util.ArrayList;

import com.trisse.spacerouge.Filer;
import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.entities.Entity;
import com.trisse.spacerouge.entities.Player;
import com.trisse.spacerouge.graphics.Screen;
import com.trisse.spacerouge.graphics.Sprites;

public class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4999487079927819553L;

	public ArrayList<Entity> entities = new ArrayList<Entity>();

	private Map map;

	public Player player;

	public Level(Sprites sprites) {
		map = (Map) Filer.loadObject("save/map.ser");

		player = new Player(3, 10, sprites);
	}

	public void init() {

	}

	public void handleInput(Game game) {
		player.handleInput(game);
	}

	public void tick(Game game) {
		double timeScale = 1;
		for (int j = 0; j < map.tiles.size(); j++) {
			player.handleCollision(map.tiles.get(j));
		}
		for (int i = 0; i < entities.size(); i++) {
			player.handleCollision(entities.get(i));
			for (int j = 0; j < map.tiles.size(); j++) {
				entities.get(i).handleCollision(map.tiles.get(j));
			}
			for (int j = i + 1; j < entities.size(); j++) {
				entities.get(i).handleCollision(entities.get(j));
			}
		}

		for (Entity entity : entities) {
			entity.tick(timeScale);
		}

		player.tick();
	}

	public void render(Screen screen) {
		map.render(screen, player);
		for (Entity e : entities) {
			e.render(screen);
		}
		player.render(screen);
	}

}
