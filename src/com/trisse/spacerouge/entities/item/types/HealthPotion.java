package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.util.*;

public class HealthPotion extends ItemType {

	private int healing;

	public HealthPotion(GenericItem generic, int healing) {
		super(generic);
		this.healing = healing;
	}

	@Override
	public boolean use(Actor actor) {
		actor.heal(healing);
		return true;
	}

	@Override
	public String getStats() {
		return null;
	}

	@Override
	public boolean isConsumed() {
		return true;
	}

}
