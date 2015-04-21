package com.trisse.spacerouge.entities;

import com.trisse.spacerouge.collections.LoadedEntity;
import com.trisse.spacerouge.entities.types.DoorType;
import com.trisse.spacerouge.graphics.Sprite;
import com.trisse.spacerouge.graphics.Sprites;

public class EntityTypeFactory {

	public static EntityType doorFactory(LoadedEntity loadedEntity, Sprites sprites) {

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

	public static EntityType floorFactory(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		Sprite sprite;
		String name;

		for (int i = 0; i < variables.length; i++) {
			switch (variables[i]) {
			case "sprites":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
			}
		}
		return null;
	}

}
