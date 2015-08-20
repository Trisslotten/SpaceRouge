package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.item.types.*;

public class Stomach {

	private double metabolism = 0.5;

	private double waterUptake = 0.5;
	private double foodToEnergyRate = 2;
	
	public double waterDecay = 1;
	public double energyDecay = 1;
	
	private double quench;
	private double energy;

	private double waterUnits;
	private double foodUnits;

	private double capacity;
	private double pukeRatio = 1.2;

	public Stomach() {
		capacity = 3000;

		waterUnits = 200;
		foodUnits = 200;

		energy = 200;
	}

	public void eat(Food food) {
		waterUnits += food.getWater();
		foodUnits += food.getFood();
	}

	private boolean isFull() {
		return waterUnits + foodUnits >= capacity;
	}

	private boolean willPuke() {
		return waterUnits + foodUnits >= capacity * pukeRatio;
	}

	public void tick(Actor actor) {
		// food to energy conversion without ratios
		if (waterUnits > 0) {
			waterUnits -= metabolism;
			quench += metabolism * waterUptake;
		}
		if (foodUnits > 0) {
			foodUnits -= metabolism;
			energy += metabolism * foodToEnergyRate;
		}
		if (quench > 0) {
			quench--;
		}
		if (energy > 0) {
			energy--;
		}

	}

}
