package com.trisse.spacerouge.util;

import java.util.*;

import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.graphics.*;

public class EntityParser {

	private static EntityType chooseType(LoadedEntity loadedEntity, Sprites sprites) {
		switch (loadedEntity.getType()) {
		case "floor":
			return EntityTypeFactory.floor(loadedEntity, sprites);
		case "wall":
			return EntityTypeFactory.wall(loadedEntity, sprites);
		case "door":
			return EntityTypeFactory.door(loadedEntity, sprites);
		default:
			return null;
		}
	}

	private static EntityType typeFromString(String str, Sprites sprites) {
		String cleaned = str.replaceAll("\\s+", "");

		String[] tileData = cleaned.split(";");

		String[][] splittedString = new String[tileData.length][2];
		for (int i = 0; i < splittedString.length; i++) {
			splittedString[i] = tileData[i].split(":");
		}
		LoadedEntity loadedEntity = LoadedEntity.newEmpty();
		for (int i = 0; i < tileData.length; i++) {
			try {
				loadedEntity.add(splittedString[i][0], splittedString[i][1]);
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Could not parse\n");
			}
		}
		return chooseType(loadedEntity, sprites);
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

}
