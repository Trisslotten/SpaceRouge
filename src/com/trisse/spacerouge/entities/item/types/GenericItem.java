package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class GenericItem extends ItemType {

	protected Sprite sprite;

	public GenericItem(String name, int id, Sprite sprite, int damage, String useMessage, String desc) {
		super(name, id, sprite, damage, useMessage, desc);
	}

	@Override
	public boolean use(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConsumed() {
		return false;
	}

}
