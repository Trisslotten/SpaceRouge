package com.trisse.spacerouge.util;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.actor.types.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.item.types.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.entities.tile.types.*;
import com.trisse.spacerouge.graphics.*;

public class EntityParser {

	private static TileType chooseTileType(LoadedEntity loadedEntity, Sprites sprites) {
		switch (loadedEntity.getType()) {
		case "dooropen":
			return doorOpen(loadedEntity, sprites);
		case "doorclosed":
			return doorClosed(loadedEntity, sprites);
		case "floor":
			return floor(loadedEntity, sprites);
		case "wall":
			return wall(loadedEntity, sprites);
		default:
			return null;
		}
	}

	private static ItemType chooseItemType(LoadedEntity loadedEntity, Sprites sprites) {
		switch (loadedEntity.getType()) {
		case "corpse":
			return corpse(loadedEntity, sprites);
		case "weapon":
			return weapon(loadedEntity, sprites);
		case "healthpotion":
			return healthPotion(loadedEntity, sprites);
		default:
			System.err.println("Could not parse item with type: " + loadedEntity.getType());
			return null;
		}
	}

	private static ActorType chooseActorType(LoadedEntity loadedEntity, Sprites sprites) {
		switch (loadedEntity.getType()) {
		case "human":
			return human(loadedEntity, sprites);
		case "alien":
			return alien(loadedEntity, sprites);
		default:
			return null;
		}
	}

	private static HealthPotion healthPotion(LoadedEntity loadedEntity, Sprites sprites) {
		String[] values = loadedEntity.values();
		String[] variables = loadedEntity.variables();
		int healing = 0;
		for (int i = 0; i < variables.length; i++) {
			switch (variables[i].toLowerCase()) {
			case "healing":
				healing = Integer.parseInt(values[i]);
				break;
			}
		}
		return new HealthPotion(genericItem(loadedEntity, sprites), healing);
	}

	private static Weapon weapon(LoadedEntity loadedEntity, Sprites sprites) {
		// parse other stuff here if needed
		return new Weapon(genericItem(loadedEntity, sprites));
	}

	private static Corpse corpse(LoadedEntity loadedEntity, Sprites sprites) {
		// parse other stuff here if needed
		return new Corpse(genericItem(loadedEntity, sprites));
	}

	private static Alien alien(LoadedEntity loadedEntity, Sprites sprites) {
		// parse other stuff here if needed
		GenericActor generic = genericActor(loadedEntity, sprites);
		return new Alien(generic);
	}

	private static Human human(LoadedEntity loadedEntity, Sprites sprites) {
		// parse other stuff here if needed
		GenericActor generic = genericActor(loadedEntity, sprites);
		return new Human(generic);
	}

	private static GenericItem genericItem(LoadedEntity loadedEntity, Sprites sprites) {
		int id = -1;
		int damage = 0;
		Sprite sprite = null;
		String name = null;
		String useMessage = null;
		String desc = null;
		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();
		for (int i = 0; i < variables.length; i++) {
			switch (variables[i].toLowerCase()) {
			case "id":
				id = Integer.parseInt(values[i]);
				break;
			case "sprite":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
				break;
			case "damage":
				damage = Integer.parseInt(values[i]);
				break;
			case "usemessage":
				useMessage = values[i];
				break;
			case "desc":
			case "description":
				desc = values[i];
				break;
			}
		}
		return new GenericItem(name, id, sprite, damage, useMessage, desc);
	}

	private static GenericActor genericActor(LoadedEntity loadedEntity, Sprites sprites) {
		int id = -1;
		int damage = 0;
		int team = -1;
		int corpse = -1;
		int health = 0;
		Sprite sprite = null;
		String name = null;
		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();
		for (int i = 0; i < variables.length; i++) {
			switch (variables[i].toLowerCase()) {
			case "id":
				id = Integer.parseInt(values[i]);
				break;
			case "sprite":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
				break;
			case "team":
				team = Integer.parseInt(values[i]);
				break;
			case "corpse":
				corpse = Integer.parseInt(values[i]);
				break;
			case "damage":
				damage = Integer.parseInt(values[i]);
				break;
			case "health":
				health = Integer.parseInt(values[i]);
			}
		}
		return new GenericActor(name, id, team, corpse, sprite, damage, health);
	}

	private static DoorClosedType doorClosed(LoadedEntity loadedEntity, Sprites sprites) {
		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();
		int id = -1;
		int opensTo = -1;
		Sprite sprite = null;
		String name = null;
		for (int i = 0; i < variables.length; i++) {
			switch (variables[i].toLowerCase()) {
			case "id":
				id = Integer.parseInt(values[i]);
				break;
			case "sprite":
				sprite = sprites.getSprite(values[i]);
				break;
			case "name":
				name = values[i];
				break;
			case "opensto":
				opensTo = Integer.parseInt(values[i]);
				break;
			}
		}
		return new DoorClosedType(name, sprite, id, opensTo);
	}

	private static DoorOpenType doorOpen(LoadedEntity loadedEntity, Sprites sprites) {

		String[] variables = loadedEntity.variables();
		String[] values = loadedEntity.values();

		int id = -1;
		int closesTo = -1;
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
			case "closesto":
				closesTo = Integer.parseInt(values[i]);
				break;
			}
		}
		return new DoorOpenType(name, sprite, id, closesTo);
	}

	private static TileType floor(LoadedEntity loadedEntity, Sprites sprites) {
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

	private static TileType wall(LoadedEntity loadedEntity, Sprites sprites) {
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

	/**
	 * 
	 */
	public static ArrayList<ActorType> actorList(Sprites sprites) {
		String path = "res/actors.cfg";
		String config = Filer.loadString(path);

		ArrayList<ActorType> entityTypes = new ArrayList<ActorType>();

		for (int i = 0; i < config.length(); i++) {
			char chr = config.charAt(i);
			if (chr == '{') {
				String currentActor = "";
				for (int j = i + 1; j < config.length(); j++) {
					char chr2 = config.charAt(j);
					if (chr2 != '}') {
						currentActor += chr2;
					} else {
						i = j;
						break;
					}
				}
				entityTypes.add(actorTypeFromString(currentActor, sprites));
			}
		}
		return entityTypes;
	}

	private static ActorType actorTypeFromString(String str, Sprites sprites) {
		LoadedEntity loadedEntity = entityListFrom(str);
		return chooseActorType(loadedEntity, sprites);
	}

	private static ItemType itemTypeFromString(String str, Sprites sprites) {
		LoadedEntity loadedEntity = entityListFrom(str);
		return chooseItemType(loadedEntity, sprites);
	}

	private static LoadedEntity entityListFrom(String loaded) {
		String cleaned = "";
		boolean saveSpace = false;
		for (int i = 0; i < loaded.length(); i++) {
			char crnt = loaded.charAt(i);
			if (crnt == '"') {
				saveSpace = !saveSpace;

			} else if (saveSpace) {
				cleaned += crnt;
			} else {
				if (crnt != ' ' && crnt != '\n' && crnt != '\t') {
					cleaned += crnt;
				}
			}
		}
		String[] entityData = cleaned.split(";");
		String[] temp = new String[entityData.length - 1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = entityData[i];
		}
		entityData = temp;

		String[][] splittedString = new String[entityData.length][2];
		for (int i = 0; i < splittedString.length; i++) {
			splittedString[i] = entityData[i].split(":");
			splittedString[i][0] = splittedString[i][0].replaceAll("\\s+", "");
		}
		LoadedEntity loadedEntity = LoadedEntity.newEmpty();
		for (int i = 0; i < entityData.length; i++) {
			try {
				loadedEntity.add(splittedString[i][0], splittedString[i][1]);
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Could not parse\n" + i);
			}
		}
		return loadedEntity;
	}

	public static ArrayList<ItemType> itemList(Sprites sprites) {
		String path = "res/items.cfg";
		String config = Filer.loadString(path);

		ArrayList<ItemType> entityTypes = new ArrayList<ItemType>();

		for (int i = 0; i < config.length(); i++) {
			char chr = config.charAt(i);
			if (chr == '{') {
				String currentActor = "";
				for (int j = i + 1; j < config.length(); j++) {
					char chr2 = config.charAt(j);
					if (chr2 != '}') {
						currentActor += chr2;
					} else {
						i = j;
						break;
					}
				}
				ItemType type = itemTypeFromString(currentActor, sprites);
				if (type != null)
					entityTypes.add(type);
			}
		}
		return entityTypes;
	}

	/**
	 * Tiles
	 */
	public static ArrayList<TileType> tileList(Sprites sprites) {
		String path = "res/tiles.cfg";
		String config = Filer.loadString(path);

		ArrayList<TileType> entityTypes = new ArrayList<TileType>();

		for (int i = 0; i < config.length(); i++) {
			char chr = config.charAt(i);
			if (chr == '{') {
				String currentActor = "";
				for (int j = i + 1; j < config.length(); j++) {
					char chr2 = config.charAt(j);
					if (chr2 != '}') {
						currentActor += chr2;
					} else {
						i = j;
						break;
					}
				}
				TileType type = tileTypeFromString(currentActor, sprites);
				if (type != null) {
					entityTypes.add(type);
				}
			}
		}
		return entityTypes;
	}

	private static TileType tileTypeFromString(String str, Sprites sprites) {
		LoadedEntity loadedEntity = entityListFrom(str);
		return chooseTileType(loadedEntity, sprites);
	}

}
