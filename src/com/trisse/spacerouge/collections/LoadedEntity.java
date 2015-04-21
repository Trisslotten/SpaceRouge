package com.trisse.spacerouge.collections;

import java.util.ArrayList;

public class LoadedEntity {
	
	private ArrayList<String> variables = new ArrayList<String>();
	private ArrayList<String> values = new ArrayList<String>();
	
	public String getType() {
		for(int i=0;i<variables.size();i++) {
			if(variables.get(i).equals("type")) {
				return values.get(i);
			}
		}
		return null;
	}
	
	public void add(String variable, String value) {
		variables.add(variable);
		values.add(value);
	}
	
	public String[] values() {
		return (String[]) values.toArray(new String[values.size()]);
	}
	
	public String[] variables() {
		return (String[]) variables.toArray(new String[variables.size()]);
	}

}
