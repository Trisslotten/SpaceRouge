package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.util.*;

public class Corpse extends ItemType {

	public Corpse(GenericItem generic) {
		super(generic);
	}

	@Override
	public boolean use(Actor actor) {
		return false;
	}
}
