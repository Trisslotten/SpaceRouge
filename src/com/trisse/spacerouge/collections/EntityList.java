package com.trisse.spacerouge.collections;

import java.util.ArrayList;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.EntityType;
import com.trisse.spacerouge.entities.tile.tiles.*;
import com.trisse.spacerouge.graphics.*;

public class EntityList {

	public ArrayList<EntityType> entityTypes;

	public EntityList(Sprites sprites) {

	}

	public ArrayList<EntityType> listFromString(String str, Sprites sprites) {
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

		return null;
	}

	public EntityType typeFromString(String str, Sprites sprites) {

		String cleaned = str.replaceAll("\\s+", "");

		String[] tileData = cleaned.split(";");

		String[][] splittedString = new String[tileData.length][2];
		for (int i = 0; i < splittedString.length; i++) {
			splittedString[i] = tileData[i].split(":");
		}

		String type = "";

		for (int i = 0; i < tileData.length; i++) {
			String variable = null;
			String value = null;
			try {
				variable = splittedString[i][0];
				value = splittedString[i][1];
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Could not parse near:\n");
			}
			if (variable.toLowerCase().equals("type")) {
				type = value;
				break;
			}
		}
		switch (type) {
		case "tile":
			return TileType.instanceFromString(str);
			break;
		case "mob":
			break;
		}

		return null;
	}

	public EntityType get(int i) {
		return entityTypes.get(i);
	}

}
