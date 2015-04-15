package com.trisse.spacerouge.entities;

public abstract class EntityType {

	protected String name;

	public static EntityType instanceFromString(String str, String type) {
		switch (type.toLowerCase()) {
		case "mob":
			return Mob.instanceFromString(str, type);
		default:
			return null;
		}
	}

}
