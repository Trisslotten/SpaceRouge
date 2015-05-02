package com.trisse.spacerouge.action;

import java.util.*;

import com.trisse.spacerouge.entities.actor.*;

public abstract class Action {

	protected Actor actor;

	public Action(Actor actor) {
		this.actor = actor;
	}
	
	public abstract void perform();

}
