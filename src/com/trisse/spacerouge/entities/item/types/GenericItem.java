package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class GenericItem extends ItemType {

	protected Sprite sprite;

	public GenericItem(String name, int id, Sprite sprite, int damage) {
		super(name, id, sprite, damage);
	}

	@Override
	public boolean use(Actor actor, StringContainer message) {
		// TODO Auto-generated method stub
		return false;
	}

}
