package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.entities.tile.tiles.*;
import com.trisse.spacerouge.entities.types.*;
import com.trisse.spacerouge.graphics.*;

public class EntityTypeFactory {

	public static EntityType door(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		int id = -1;
		Sprite open = null, closed = null;
		String name = null;

		for (int i = 0; i < variables.length; i++) {
			switch (variables[i]) {
			case "id":
				id = Integer.parseInt(values[i]);
				break;
			case "open":
				open = sprites.getSprite(values[i]);
				break;
			case "closed":
				closed = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
				break;
			
			}
		}
		return new DoorType(name, open, closed, id);
	}

	public static EntityType floor(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		int id = -1;
		Sprite sprite = null;
		String name = null;

		for (int i = 0; i < variables.length; i++) {
			switch (variables[i]) {
			case "id":
				id = Integer.parseInt(values[i]);
				break;
			case "sprite":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
				break;
			}
		}
		return new FloorType(name, sprite, id);
	}

	public static EntityType wall(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		int id = -1;
		Sprite sprite = null;
		String name = null;

		for (int i = 0; i < variables.length; i++) {
			switch (variables[i]) {
			case "id":
				id = Integer.parseInt(values[i]);
				break;
			case "sprite":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
				break;
			}
		}

		return new WallType(name, sprite, id);
	}

}
