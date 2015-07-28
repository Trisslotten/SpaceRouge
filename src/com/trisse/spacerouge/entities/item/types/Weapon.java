package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class Weapon extends ItemType {

	public Weapon(String name, int id, Sprite sprite, int damage, String useMessage) {
		super(name, id, sprite, damage, useMessage);
		// TODO Auto-generated constructor stub
	}

	public Weapon(GenericItem generic) {
		super(generic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean use(Actor actor) {
		return false;
	}

}
