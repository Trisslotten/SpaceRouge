package com.trisse.spacerouge.level;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;

public class Area {

	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public ArrayList<Actor> actors = new ArrayList<Actor>();

	public ArrayList<ItemEntity> items = new ArrayList<ItemEntity>();

	public ArrayList<Entity> getEntities() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		entities.addAll(tiles);
		entities.addAll(actors);
		entities.addAll(items);
		return entities;
	}

	public void addItem(ItemType type, int x, int y) {
		items.add(new ItemEntity(new Item(type), x, y));
	}

	public void addTile(TileType type, int x, int y) {
		tiles.add(new Tile(type, x, y));
	}

	public void addActor(ActorType type, int x, int y) {
		actors.add(new Actor(type, x, y, this));
	}

	public Area(Game game) {
		createTestingArea(game);
	}

	public Area() {

	}

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

	public void render(Screen screen, int xoffset, int yoffset) {
		for (Tile t : tiles) {
			t.render(screen, xoffset, yoffset);
		}
		for (ItemEntity i : items) {
			i.render(screen, xoffset, yoffset);
		}
		for(Actor a: actors) {
			a.render(screen, xoffset, yoffset);
		}
	}

	public void addItem(Item item, int x, int y) {
		if (item != null)
			items.add(new ItemEntity(item, x, y));
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

	public ArrayList<Tile> getTilesNextTo(int x, int y) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		for (Tile t : tiles) {
			for (int i = -1; i <= 1; i += 2) {
				if ((t.x() == x + i && t.y() == y) || (t.x() == x && t.y() == y + i)) {
					result.add(t);
				}
			}
		}
		return result;
	}

	public ArrayList<Tile> getTilesAround(int x, int y) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		for (Tile t : tiles) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (t.x() == x + i && t.y() == y + j) {
						result.add(t);
					}
				}

			}
		}
		return result;
	}

	public ArrayList<Entity> getEntitiesOn(int x, int y) {
		ArrayList<Entity> result = new ArrayList<Entity>();
		for (Actor t : actors) {
			if (t.x() == x && t.y() == y) {
				result.add(t);
			}
		}
		for (Tile t : tiles) {
			if (t.x() == x && t.y() == y) {
				result.add(t);
			}
		}
		for (ItemEntity t : items) {
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

	public Actor getPlayer() {
		for (Actor a : actors) {
			if (a.isPlayer) {
				return a;
			}
		}
		return null;
	}

	public Item getAndRemoveItem(int x, int y, Direction dir) {
		ArrayList<Item> result = new ArrayList<Item>();
		ArrayList<ItemEntity> toRemove = new ArrayList<ItemEntity>();
		for (ItemEntity i : items) {
			if (i.x() == x && i.y() == y) {
				result.add(i.getItem());
				toRemove.add(i);
			}
		}
		items.removeAll(toRemove);
		if (!result.isEmpty())
			return result.get(0);
		else
			return null;
	}

	public int entityCount() {
		return tiles.size() + actors.size() + items.size();
	}

}
