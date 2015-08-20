package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.entities.tile.*;

public class Breathing {
		
	private double o2Perc;
	
	private double o2UseRatio = 0.8; 

	private double faintLevel = 0.5;
	
	private double deathLevel = 0.2;
	
	public Breathing() {
		o2Perc = 1.0;
	}
	
	public void tick(Actor actor) {
		Tile occupied = actor.getOccupiedTile();
		if(occupied == null) {
			o2Perc *= o2UseRatio;
		} else {
			occupied.getO2Level();
		}
		
		if(o2Perc < deathLevel) {
			actor.kill("You died of hypoxia");
		} else if(o2Perc < faintLevel) {
			actor.faint("You fainted");
		}
		
	}
	
}
