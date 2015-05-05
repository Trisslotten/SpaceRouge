package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.graphics.*;

public class Actor {

	protected Action currentAction, plannedAction;

	protected ActorType type;

	protected int energy = 10;
	
	protected int energySpeed = 3;

	protected int x, y;

	public Actor() {

	}

	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void think() {
		addEnergy();
		
		planAction();
		
		handleActions();
	}

	protected void planAction() {
		plannedAction = new WalkAction(this);
	}
	
	protected void addEnergy() {
		energy += energySpeed;
	}
	
	protected void handleActions() {
		if(plannedAction.canPay(energy)) {
			energy -= plannedAction.getCost();
			currentAction = plannedAction;
		} else {
			currentAction = new WaitAction(this);
		}
	}

	public Action getAction() {
		Action action = currentAction;
		currentAction = null;
		return action;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		screen.draw("player", x, y);
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public void move(Direction dir) {
		x += dir.xspd();
		y += dir.yspd();
	}

	public void setAction(Action action) {
		currentAction = action;
	}

}
