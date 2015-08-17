package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.item.types.*;

public class Stomach {
	
	private int quench;
	private int energy;
	
	private int waterUnits;
	private int foodUnits;
	
	private int capacity;

	public Stomach() {
		capacity = 1000;
		waterUnits = 200;
	}

	public void eat(Food food) {
		waterUnits += food.getWater();
		foodUnits += food.getFood();
	}
	
	public void tick() {
		//food to energy conversion without ratios
		if(waterUnits>0) {
			waterUnits--;
			quench++;
		}
		if(foodUnits>0) {
			foodUnits--;
			energy++;
		}
		if(quench>0) {
			quench--;
		}
		if(energy>0) {
			energy--;
		}
	}

}
