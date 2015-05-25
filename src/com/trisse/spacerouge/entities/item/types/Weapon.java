package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

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
	public boolean use(Actor actor, StringContainer message) {
		message.setString(CANT_USE);
		return false;
	}

}
