package com.trisse.spacerouge.collections;

import java.util.ArrayList;

import com.trisse.spacerouge.FileLoader;
import com.trisse.spacerouge.entities.EntityTemplate;
import com.trisse.spacerouge.graphics.Sprites;

public class Entities {

	public EntityTemplate[] entities;
	
	public Entities(Sprites sprites) {
		entities = getEntitiesFromFile(sprites);
	}
	

	private EntityTemplate parseEntityTemplate(String currentEntity, Sprites sprites) {
		String cleaned = currentEntity.replaceAll("\\s+", "");

		String[] tileData = cleaned.split(";");

		String[][] splittedString = new String[tileData.length][2];
		for (int i = 0; i < splittedString.length; i++) {
			splittedString[i] = tileData[i].split(":");
		}
		
		
		return null;
	}

	public EntityTemplate[] getEntitiesFromFile(Sprites sprites) {

		String config = FileLoader.loadString("res/tiles.cfg");

		ArrayList<EntityTemplate> entities = new ArrayList<EntityTemplate>();
		for (int i = 0; i < config.length(); i++) {
			char chr = config.charAt(i);

			if (chr == '{') {
				String currentEntity = "";
				for (int j = i + 1; j < config.length(); j++) {
					char chr2 = config.charAt(j);
					if (chr2 != '}') {
						currentEntity += chr2;
					} else {
						i = j;
						break;
					}
				}

				entities.add(parseEntityTemplate(currentEntity, sprites));

			}

		}

		return (EntityTemplate[]) entities.toArray(new EntityTemplate[entities.size()]);

	}
}
