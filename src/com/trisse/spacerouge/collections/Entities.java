package com.trisse.spacerouge.collections;

import com.trisse.spacerouge.entities.*;

public class Entities {

	public Entity[] entities;

	public Entity get(int i) {
		return entities[i];
	}

	public int length() {
		return entities.length;
	}

}
