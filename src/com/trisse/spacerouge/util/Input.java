package com.trisse.spacerouge.util;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.trisse.spacerouge.graphics.Screen;

public class Input {

	public static boolean controlPressed() {
		Keyboard.next();
		return controlPressed(Keyboard.getEventKey());
	}

	public static boolean controlPressed(int eventKey) {
		if (Keyboard.getEventKeyState()) {
			switch (eventKey) {
			case Keyboard.KEY_UP:
			case Keyboard.KEY_DOWN:
			case Keyboard.KEY_RIGHT:
			case Keyboard.KEY_LEFT:
			case Keyboard.KEY_PERIOD:
			case Keyboard.KEY_C:
				return true;
			}
		}
		return false;
	}

	public boolean[] keys = new boolean[Keyboard.KEYBOARD_SIZE];

	public boolean[] buttons = new boolean[3];

	private int lastx, lasty;

	public int dxt() {
		return dy() / Screen.tileSize;
	}

	public int dyt() {
		return dy() / Screen.tileSize;
	}

	public int dx() {
		return (x() - lastx);
	}

	public int dy() {
		return (y() - lasty);
	}

	public int xt() {
		return x() / Screen.tileSize;
	}

	public int yt() {
		return y() / Screen.tileSize;
	}

	public int x() {
		return Mouse.getX();
	}

	public int y() {
		return Display.getHeight() - Mouse.getY();
	}

	public boolean keyDown(int key) {
		return Keyboard.isKeyDown(key);
	}

	public boolean keyPressed(int key) {
		return Keyboard.isKeyDown(key) && !keys[key];
	}

	public boolean keyUp(int key) {
		return !Keyboard.isKeyDown(key) && keys[key];
	}

	public boolean mouseDown(int button) {
		return Mouse.isButtonDown(button);
	}

	public boolean mousePressed(int button) {
		return Mouse.isButtonDown(button) && !buttons[button];
	}

	public boolean mouseUp(int button) {
		return !Mouse.isButtonDown(button) && buttons[button];
	}

	public void setKeys() {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = Keyboard.isKeyDown(i);
		}
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = Mouse.isButtonDown(i);
		}
		lastx = x();
		lasty = y();
	}

}
