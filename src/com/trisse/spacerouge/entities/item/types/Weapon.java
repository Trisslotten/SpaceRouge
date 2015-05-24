package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.graphics.*;

public class Weapon extends ItemType {

	public Weapon(String name, int id, Sprite sprite, int damage) {
		super(name, id, sprite, damage);
		// TODO Auto-generated constructor stub
	}

	public Weapon(GenericItem generic) {
		super(generic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String use(Actor actor) {
		return CANT_USE;
	}

}
