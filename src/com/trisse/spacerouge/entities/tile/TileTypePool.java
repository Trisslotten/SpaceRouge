package com.trisse.spacerouge.entities.tile;

import java.util.*;

import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class TileTypePool {

	public ArrayList<TileType> tileTypes;

	public EntityType[] entityTypes() {
		return (EntityType[]) tileTypes.toArray(new EntityType[tileTypes.size()]);
	}

	public TileTypePool(Sprites sprites) {
		tileTypes = EntityParser.tileList(sprites);
	}

	public TileType get(int i) {
		return tileTypes.get(i);
	}

	public TileType getType(int id) {
		for (TileType e : tileTypes) {
			if (e.getID() == id) {
				return e;
			}
		}
		return null;
	}

	public TileType getType(String type) {
		for (TileType e : tileTypes) {
			if (e.isType(type)) {
				return e;
			}
		}
		return null;
	}

}
