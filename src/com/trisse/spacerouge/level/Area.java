package com.trisse.spacerouge.level;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class Area {
	
	public static int width = 16;
	public static int height = 16;
	
	
	private Game game;
	
	private TileList[][] tiles;
	
	private ItemList[][] items;

	
	/*
	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public ArrayList<Actor> actors = new ArrayList<Actor>();

	public ArrayList<ItemEntity> items = new ArrayList<ItemEntity>();
	*/

	public void addItem(ItemType type, int x, int y) {
		items[x][y].add(ItemEntity.newItemEntity(type));
	}

	public void addTile(TileType type, int x, int y) {
		tiles[x][y].add(new Tile(type));
	}

	public void addActor(ActorType type, int x, int y) {
		
	}

	public Area(Game game) {
		this.game = game;
		initArrays();
		addFloor();
	}

	private void initArrays() {
		tiles = new TileList[width][height];
		for(int i=0;i<tiles.length;i++) {
			for(int j = 0;j < tiles[i].length;j++) {
				tiles[i][j] = new TileList();
			}
		}
		items = new ItemList[width][height];
		for(int i=0;i<items.length;i++) {
			for(int j = 0;j < items[i].length;j++) {
				items[i][j] = new ItemList();
			}
		}
	}
	
	private void addFloor() {
		for(int i=0;i<tiles.length;i++) {
			for(int j = 0;j < tiles[i].length;j++) {
				tiles[i][j].add(new Tile(game.tilePool.get(5)));
			}
		}
	}

	/*
	private void createTestingArea(Game game) {
		ActorTypePool actorPool = game.actorPool;
		TileTypePool tilePool = game.tilePool;
		int x = 2;
		for (ItemType i : game.itemPool.itemTypes) {
			addItem(i, x, -4);
			x++;
		}
		x = 2;
		for (TileType i : game.tilePool.tileTypes) {
			addTile(i, x, -2);
			x++;

		}

		actors.add(new Player(actorPool.getType("human"), -5, -5, this));
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				ActorType type = null;
				switch (Game.rand.nextInt(2)) {
				case 0:
					type = actorPool.getType("large alien");
					break;
				case 1:
					type = actorPool.getType("small alien");
					break;
				}
				actors.add(new Actor(type, i * 2 + 1, j * 2 + 1, this));

			}
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (j == 3 && i == 0) {
					tiles.add(new Tile(tilePool.getType("door"), i, j));
					tiles.add(new Tile(tilePool.getType("floor"), i, j));
				} else if (j == 0 || j == 10 || i == 0 || i == 10) {
					tiles.add(new Tile(tilePool.getType("hull"), i, j));
				} else {
					tiles.add(new Tile(tilePool.getType("floor"), i, j));
				}
			}
		}
		for (Actor a : actors) {
			a.init();
			if (a.isPlayer) {
				game.setOffsetToActor(a);
			}
		}
	}
	*/

	

	public void render(Screen screen, int xoffset, int yoffset) {
		System.out.println("render");
		for(int y=0;y<tiles.length;y++) {
			for(int x = 0; x< tiles.length;x++) {
				for (Tile t : tiles[x][y]) {
					t.render(screen, x - xoffset, y - yoffset);
				}
				for (ItemEntity i : items[x][y]) {
					i.render(screen, x - xoffset, y - yoffset);
				}
			}
		}
	}

	public void addItem(Item item, int x, int y) {
		if (item != null)
			items[x][y].add(new ItemEntity(item));
	}

	public boolean isPassable(int x, int y) {
		for (Tile t : tiles[x][y]) {
			if (t.isPassable()) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Item> getItems(int x, int y) {
		ArrayList<Item> result = new ArrayList<Item>(items[x][y].size());
		for(ItemEntity e: items[x][y]) {
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

	public ArrayList<Tile> getTiles(int areax, int areay) {
		return tiles[areax][areay];
	}

}
