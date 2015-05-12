package com.trisse.spacerouge.level;

import java.util.ArrayList;

import com.trisse.spacerouge.entities.actor.Actor;
import com.trisse.spacerouge.entities.item.Item;
import com.trisse.spacerouge.entities.item.ItemEntity;
import com.trisse.spacerouge.entities.tile.Tile;
import com.trisse.spacerouge.entities.tile.TileTypePool;
import com.trisse.spacerouge.graphics.Screen;

public class Area {

	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public ArrayList<Actor> actors = new ArrayList<Actor>();
	
	public ArrayList<ItemEntity> items = new ArrayList<ItemEntity>();

	public Area(TileTypePool tilePool) {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (j == 3 && i == 0) {
					tiles.add(new Tile(tilePool.getType("doorclosed"), i, j));
					tiles.add(new Tile(tilePool.getType("floor"), i, j));
				} else if (j == 0 || j == 10 || i == 0 || i == 10) {
					tiles.add(new Tile(tilePool.getType("hull"), i, j));
				} else {
					tiles.add(new Tile(tilePool.getType("floor"), i, j));
				}
			}
		}
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		for (Tile t : tiles) {
			t.render(screen, xoffset, yoffset);
		}
	}
	
	public void addItem(Item item, int x, int y) {
		items.add(new ItemEntity(item,x,y));
	}

	public boolean isPassable(int x, int y) {
		for (Tile t : tiles) {
			if (t.x() == x && t.y() == y && t.isPassable()) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Tile> getTilesOn(int x, int y) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		for (Tile t : tiles) {
			if (t.x() == x && t.y() == y) {
				result.add(t);
			}
		}
		return result;
	}

	public ArrayList<Actor> getActorsOn(int x, int y) {
		ArrayList<Actor> result = new ArrayList<Actor>();
		for (Actor t : actors) {
			if (t.x() == x && t.y() == y) {
				result.add(t);
			}
		}
		return result;
	}

}
