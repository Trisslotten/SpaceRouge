package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;

public class Corpse extends ItemType {

	public Corpse(GenericItem generic) {
		super(generic);
	}

	@Override
	public String use(Actor actor) {
		return CANT_USE;
	}
}
