package com.trisse.spacerouge.util;

import java.util.ArrayList;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.collections.LoadedEntity;
import com.trisse.spacerouge.entities.EntityType;
import com.trisse.spacerouge.entities.EntityTypeFactory;
import com.trisse.spacerouge.entities.tile.tiles.FloorType;
import com.trisse.spacerouge.graphics.Sprites;

public class EntityParser {

	private static EntityType parseMob(LoadedEntity loadedEntity, Sprites sprites) {
		switch (loadedEntity.getType()) {
		default:

			Game.underConstruction(EntityParser.class);

		}
		return null;
	}

	private static EntityType parseTile(LoadedEntity loadedEntity, Sprites sprites) {
		switch (loadedEntity.getType()) {
		case "floor":
			return EntityTypeFactory.floor(loadedEntity, sprites);
		case "Wall":
			return EntityTypeFactory.wall(loadedEntity, sprites);
		}
		return null;
	}

	public static ArrayList<EntityType> listFromString(String str, Sprites sprites) {
		ArrayList<EntityType> entityTypes = new ArrayList<EntityType>();
		String config = Filer.loadString("res/entities.cfg");
		for (int i = 0; i < config.length(); i++) {
			char chr = config.charAt(i);
			if (chr == '{') {
				String currentTile = "";
				for (int j = i + 1; j < config.length(); j++) {
					char chr2 = config.charAt(j);
					if (chr2 != '}') {
						currentTile += chr2;
					} else {
						i = j;
						break;
					}
				}
				entityTypes.add(typeFromString(currentTile, sprites));
			}
		}
		return entityTypes;
	}

	private static EntityType typeFromString(String str, Sprites sprites) {
		String cleaned = str.replaceAll("\\s+", "");

		String[] tileData = cleaned.split(";");

		String[][] splittedString = new String[tileData.length][2];
		for (int i = 0; i < splittedString.length; i++) {
			splittedString[i] = tileData[i].split(":");
		}

		String type = "";
		LoadedEntity loadedEntity = new LoadedEntity();

		for (int i = 0; i < tileData.length; i++) {
			String variable = null;
			String value = null;
			try {
				loadedEntity.add(splittedString[i][0], splittedString[i][1]);
				variable = splittedString[i][0];
				value = splittedString[i][1];
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Could not parse\n");
			}
			if (variable.toLowerCase().equals("type")) {
				type = value.toLowerCase();
			}
		}
		switch (type) {
		case "tile":
			return parseTile(loadedEntity, sprites);
		case "mob":
			return parseMob(loadedEntity, sprites);
		}
		return null;
	}

}
