package com.trisse.spacerouge.entities.item;

import java.util.*;

import com.trisse.spacerouge.graphics.*;

public class Items {

	private ArrayList<ItemEntity> items = new ArrayList<ItemEntity>();
	private ArrayList<ItemEntity> activeItems = new ArrayList<ItemEntity>();

	private ArrayList<ItemEntity> total = new ArrayList<ItemEntity>();

	public ArrayList<ItemEntity> items() {
		total.clear();
		total.addAll(items);
		total.addAll(activeItems);
		return total;
	}

	public void render(Screen screen, int xoffset, int yoffset) {

	}

}
