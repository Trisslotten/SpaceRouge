package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.entities.tile.tiles.*;
import com.trisse.spacerouge.entities.types.*;
import com.trisse.spacerouge.graphics.*;

public class EntityTypeFactory {

	public static EntityType door(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		Sprite open = null, closed = null;
		String name = null;

		for (int i = 0; i < variables.length; i++) {
			switch (variables[i]) {
			case "open":
				open = sprites.getSprite(values[i]);
				break;
			case "closed":
				closed = sprites.getSprite(values[i]);
			case "name":
				name = values[i];
			}
		}
		return new DoorType(name, open, closed);
	}

	public static EntityType floor(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		Sprite sprite = null;
		String name = null;

		for (int i = 0; i < variables.length; i++) {
			switch (variables[i]) {
			case "sprite":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
			}
		}
		return new FloorType(name, sprite);
	}

	public static EntityType wall(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		Sprite sprite = null;
		String name = null;

		for (int i = 0; i < variables.length; i++) {
			switch (variables[i]) {
			case "sprite":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
			}
		}

		return new WallType(name, sprite);
	}

}
