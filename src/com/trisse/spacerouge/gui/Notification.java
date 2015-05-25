package com.trisse.spacerouge.gui;

import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.util.*;

public class Notification extends Element {

	private StringContainer message;

	public Notification(StringContainer message) {
		this.message = message;
	}

	@Override
	public void render(Screen screen) {
		screen.drawString(message.getString(), 1, 5);
	}

}
