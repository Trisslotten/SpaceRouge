package com.trisse.spacerouge.collections;

import com.trisse.spacerouge.entities.EntityTemplate;

public class Entities {

	public EntityTemplate[] entities;
	
	public EntityTemplate get(int i) {
		return entities[i];
	}

	public int length() {
		return entities.length;
	}

}
