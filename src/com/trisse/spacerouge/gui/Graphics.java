package com.trisse.spacerouge.gui;

import java.util.ArrayList;

import com.trisse.spacerouge.Game;
import com.trisse.spacerouge.graphics.Screen;

public class Graphics {

	ArrayList<Element> elements = new ArrayList<Element>();

	ArrayList<Notification> notifications = new ArrayList<Notification>();

	public Graphics(Game game) {
		elements.add(new HealthMeter(game.area.getPlayer()));
		elements.add(new ItemViewer(game.area.getPlayer()));
	}

	public void render(Screen screen) {
		for (Element e : elements) {
			e.render(screen);
		}
		int drawy = 0;
		for (int i = 0; i < notifications.size() && i < 10; i++) {
			String noti = notifications.get(notifications.size() - i - 1).getCompleteMessage();
			int rows = screen.getRows(noti, Screen.guiWidth);
			drawy -= screen.drawStringInGui(noti, Screen.tileWidth - Screen.guiWidth, Screen.tileHeight + drawy - rows) + 1;
		}
	}

	public void addNotification(String message) {
		if (message != null) {
			if (!message.isEmpty()) {
				if (notifications.isEmpty()) {
					notifications.add(new Notification(message));
				} else {
					Notification noti = notifications.get(notifications.size() - 1);
					String notiMessage = noti.getMessage();
					if (notiMessage.equals(message)) {
						noti.addCounter();
					} else {
						notifications.add(new Notification(message));
					}
				}
			}
		}
	}

}
