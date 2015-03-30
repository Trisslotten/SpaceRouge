package com.trisse.spacerouge.entities;

import java.util.ArrayList;

import com.trisse.spacerouge.graphics.Screen;

public class StaticEntity {

	double x, y;

	public void render(Screen screen) {
		
	}

	public void tick(double time, ArrayList<StaticEntity> entities) {
		
	}

	public int xpos() {
		return (int) x;
	}

	public int ypos() {
		return (int) y;
	}
}
