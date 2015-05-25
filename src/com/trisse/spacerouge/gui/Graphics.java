package com.trisse.spacerouge.gui;

import java.util.ArrayList;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.graphics.Screen;

public class Graphics {

	ArrayList<Element> elements = new ArrayList<Element>();

	public Graphics(Game game) {
		elements.add(new HealthMeter(game.area.getPlayer()));
		elements.add(new ItemViewer(game.area.getPlayer()));
		elements.add(new Notification(game.notification));
	}

	public void render(Screen screen) {
		for (Element e : elements) {
			e.render(screen);
		}
	}

}
