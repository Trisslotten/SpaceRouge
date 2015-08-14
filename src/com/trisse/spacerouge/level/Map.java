package com.trisse.spacerouge.level;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class Map {

	private Game game;

	private Area[][] areas;

	private ArrayList<Actor> actors = new ArrayList<Actor>();

	public Map(Game game) {
		this.game = game;

		initAreas();

		ActorType actor = game.actorPool.get(0);
		actors.add(new Player(actor, 500, 500, this));

	}

	private void initAreas() {
		areas = new Area[10][10];
		for (int i = 0; i < areas.length; i++) {
			for (int j = 0; j < areas[i].length; j++) {
				areas[i][j] = new Area(game);
			}
		}
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public Item getAndRemoveItem(int x, int y, Direction dir, int index) {
		int mapx = x / Area.width;
		int mapy = y / Area.height;
		int areax = x % Area.width;
		int areay = y % Area.height;
		return areas[mapx][mapy].getAndRemoveItem(areax + dir.xspd(), areay + dir.yspd(), index);
	}

	public ArrayList<Tile> getTiles(int x, int y) {
		int mapx = x / Area.width;
		int mapy = y / Area.height;
		int areax = x % Area.width;
		int areay = y % Area.height;
		return areas[mapx][mapy].getTiles(areax, areay);
	}

	public void addItem(Item item, int x, int y) {
		int mapx = x / Area.width;
		int mapy = y / Area.height;
		int areax = x % Area.width;
		int areay = y % Area.height;
		areas[mapx][mapy].addItem(item, areax, areay);
	}

	
	//TODO FIX plz
	public void render(Screen screen, int xoffset, int yoffset) {
		int mapx = xoffset / Area.width;
		int mapy = yoffset / Area.height;

		int mapStartx = mapx - Screen.tileWidth / (Area.width * 2);
		int mapStarty = mapy - Screen.tileHeight / (Area.height * 2);

		int mapEndx = mapx + Screen.tileWidth / (Area.width * 2);
		int mapEndy = mapy + Screen.tileHeight / (Area.height * 2);
		for (int y = mapStarty-1; y < mapEndy; y++) {

			for (int x = mapStartx-1; x < mapEndx; x++) {

				int rx = 0;
				int ry = 0;
				
				areas[x][y].render(screen, rx, ry);

			}
		}

	}

	public Actor getPlayer() {
		for (Actor a : actors) {
			if (a.isPlayer)
				return a;
		}
		return null;
	}

	public ArrayList<Actor> getActors(int x, int y) {
		ArrayList<Actor> result = new ArrayList<Actor>();
		for (Actor a : actors) {
			if (a.x() == x && a.y() == y)
				result.add(a);
		}
		return result;
	}

}
