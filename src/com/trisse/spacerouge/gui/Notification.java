package com.trisse.spacerouge.gui;

public class Notification {

	private String message;
	private int count = 1;

	public Notification(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getCompleteMessage() {
		if (count > 1)
			return message + " x" + count;
		else
			return message;
	}

	public void addCounter() {
		count++;
	}

}
