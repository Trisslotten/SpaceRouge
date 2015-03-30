
package com.trisse.spacerouge;

import org.lwjgl.input.Keyboard;

public class Input {
	
	public boolean[] keys = new boolean[Keyboard.KEYBOARD_SIZE];
	
	public boolean down(int key) {
		return Keyboard.isKeyDown(key);
	}
	
	public boolean pressed(int key) {
		return Keyboard.isKeyDown(key) && !keys[key];
	}
	
	public boolean up(int key) {
		return !Keyboard.isKeyDown(key) && keys[key];
	}
	
	public void setKeys() {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = Keyboard.isKeyDown(i);
		}
	}
	
}
