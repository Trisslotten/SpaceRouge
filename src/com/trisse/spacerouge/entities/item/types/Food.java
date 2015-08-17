package com.trisse.spacerouge.entities.item.types;

import com.trisse.spacerouge.entities.actor.*;
import com.trisse.spacerouge.entities.item.*;

public class Food extends ItemType {
	
	//values are per unit
	private int food;
	private int water;
	
	private int units;

	public Food(GenericItem generic, int food, int water, int units) {
		super(generic);
		this.food = food;
		this.water = water;
		this.units = units;
	}

	@Override
	public boolean use(Actor actor) {
		actor.eat(this);
		units--;
		return true;
	}

	@Override
	public String getStats() {
		return "Nutrition: " + food*units + "\nQuench: " + water*units;
	}

	@Override
	public boolean isConsumed() {
		return units<=0;
	}

	public int getWater() {
		return water;
	}
	
	public int getFood() {
		return food;
	}
	

}
