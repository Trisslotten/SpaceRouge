package com.trisse.spacerouge.level;

import java.util.*;
import java.util.function.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class Map {

	private Game game;

	private TileType[][] floors;

	public static int width = 1024;
	public static int height = 1024;

	private TileList tiles = new TileList();

	private ItemList items = new ItemList();

	private ArrayList<Actor> actors = new ArrayList<Actor>();

	public Map(Game game) {
		this.game = game;

		initArrays();

		generateTerrain();

		ActorType actor = game.actorPool.get(0);
		actors.add(new Player(actor, 20, 20, this));

	}

	public void addNewItem(ItemType type, int x, int y) {
		items.add(ItemEntity.newItemEntity(type, x, y));
	}

	public void addNewTile(TileType type, int x, int y) {
		tiles.add(new Tile(type, x, y));
	}

	public void addStuff() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0 || j == 9 || i == 0 || i == 9) {
					addNewTile(game.tilePool.get(0), i, j);
				} else {
					setFloorType(game.tilePool.get(3), i, j);
				}
			}
		}
	}

	public Player getPlayer() {
		for (Actor a : actors) {
			if (a.isPlayer) {
				return (Player) a;
			}
		}
		return null;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		int xstart = xoffset;
		int xend = xoffset + Screen.tileWidth;
		int ystart = yoffset;
		int yend = yoffset + Screen.tileHeight;
		for (int y = ystart; y < floors.length && y < yend; y++) {
			if (y < 0) {
				continue;
			}
			for (int x = xstart; x < floors[y].length && x < xend; x++) {
				if (x >= 0 && floors[x][y] != null) {
					floors[x][y].render(screen, x - xoffset, y - yoffset);
				}
			}
		}
		for(Actor a: actors) {
			a.render(screen, xoffset, yoffset);
		}
		for (Tile t : tiles) {
			t.render(screen, xoffset, yoffset);
		}

		for (ItemEntity i : items) {
			i.render(screen, xoffset, yoffset);
		}
	}

	private void initArrays() {
		floors = new TileType[width][height];

	}

	private void generateTerrain() {
		for (int i = 0; i < floors.length; i++) {
			for (int j = 0; j < floors[i].length; j++) {
				setFloorType(game.tilePool.get(5), i, j);
			}
		}
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public ArrayList<Actor> getActors(int x, int y) {
		ArrayList<Actor> result = new ArrayList<Actor>();
		for (Actor a : actors) {
			if (a.on(x, y)) {
				result.add(a);
			}
		}
		return result;
	}

	public void addNewItem(Item item, int x, int y) {
		if (item != null)
			items.add(new ItemEntity(item, x, y));
	}

	public void setFloorType(TileType type, int x, int y) {
		floors[x][y] = type;
	}

	public boolean isPassable(int x, int y) {
		for (Tile t : tiles) {
			if (t.isPassable() && t.x() == x && t.y() == y) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Item> getItems(int x, int y) {
		ArrayList<Item> result = new ArrayList<Item>(items.size());
		for (ItemEntity e : items) {
			if (e.on(x, y))
				result.add(e.getItem());
		}
		return result;
	}

	public ArrayList<Item> getItems(int x, int y, Direction dir) {
		return getItems(x + dir.xspd(), y + dir.yspd());
	}

	public Item getAndRemoveItem(int x, int y, int index) {
		ArrayList<Item> items = getItems(x, y);
		Item result = items.get(index);
		items.remove(index);
		return result;
	}

	public ArrayList<Tile> getTiles(int x, int y) {
		ArrayList<Tile> result = new ArrayList<Tile>(tiles.size());
		for (Tile e : tiles) {
			if (e.on(x, y))
				result.add(e);
		}
		return result;
	}

	public Tile getTile(int x, int y) {
		for (Tile e : tiles) {
			if (e.on(x, y))
				return e;
		}
		return null;
	}

}
