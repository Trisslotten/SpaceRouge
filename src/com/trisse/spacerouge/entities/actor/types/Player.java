package com.trisse.spacerouge.entities.actor.types;

import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.actor.*;

public class Player extends Actor {

	public Player(int i, int j) {
		x = i;
		y = j;
	}

	public void think() {
		addEnergy();
		
		planAction();
		
		handleActions();
	}

	protected void planAction() {
		plannedAction = new PlayerWalkAction(this);
	}
	
	protected void addEnergy() {
		energy += energySpeed;
	}
	
	protected void handleActions() {
		if(plannedAction.canPay(energy)) {
			currentAction = plannedAction;
		} else {
			currentAction = new WaitAction(this);
		}
	}

}
