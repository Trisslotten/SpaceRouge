package com.trisse.spacerouge.level;

import java.util.ArrayList;

import com.trisse.spacerouge.collections.EntityTypePool;
import com.trisse.spacerouge.entities.Entity;
import com.trisse.spacerouge.entities.EntityType;
import com.trisse.spacerouge.util.Filer;

public class LevelEditorMap {
	
	private ArrayList<IDEntityType> map = new ArrayList<IDEntityType>();
	
	public void save(ArrayList<Entity> entities) { 
		for(Entity e: entities) {
			map.add(new IDEntityType(e));
		}
		Filer.SaveObject(map, "save/editorMap.lem");
	}
	
	public void export(EntityTypePool list) {
		Filer.SaveObject(getMap(list), "save/map.map");
	}
	
	public void load() {
		
	}

	private ArrayList<Entity> getMap(EntityTypePool list) {
		ArrayList<Entity> entities = new ArrayList<Entity>(); 
		
		for(IDEntityType e : map) {
			int id = e.id;
			for(EntityType t: list.entityTypes) {
				if(t.getID() == id) {
					entities.add(new Entity(t,e.x,e.y));
					break;
				}
			}
		}
		return entities;
	}
	
	
	private class IDEntityType {
		public int id, x, y;
		public IDEntityType(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}
		
		public IDEntityType(Entity e) {
			id = e.typeID();
			x = e.xpos();
			y = e.ypos();
		}
	}
}
