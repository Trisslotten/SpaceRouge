package com.trisse.spacerouge.entities.actor;

import java.util.*;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.level.*;

public class Actor extends Entity {

	protected Action action;

	protected ActorType type;

	protected Item inHands;

	public boolean isPlayer = false;

	protected int health = 2;
	protected int damage = 1;

	private Area area;

	protected int damagedCounter = 0;

	static Random rand = new Random();

	public Actor(ActorType type, int x, int y, Area area) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.area = area;
	}

	public void init() {

	}

	public void think() {

		switch (rand.nextInt(4)) {
		case 0:
			walk(Direction.UP);
			break;
		case 1:
			walk(Direction.DOWN);
			break;
		case 2:
			walk(Direction.LEFT);
			break;
		case 3:
			walk(Direction.RIGHT);
			break;
		default:
			walk(Direction.NONE);
		}
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void walk(Direction dir) {
		setNextAction(new WalkAction(this, area, dir));
	}

	public boolean isDead() {
		return health <= 0;
	}

	public void attack(Actor enemy) {
		enemy.doDamage(damage);
	}

	public void setNextAction(Action action) {
		this.action = action;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		if (damagedCounter > 0) {
			screen.draw("damaged", x - xoffset, y - yoffset, Levels.ACTOR);
			damagedCounter--;
		} else {
			screen.draw(type.currentSprite(), x - xoffset, y - yoffset, Levels.ACTOR);
		}
	}

	public void doDamage(int damage) {
		health -= damage;
		damagedCounter = 5;
	}

	public int getHealth() {
		return health;
	}

	public void move(Direction dir) {
		x += dir.xspd();
		y += dir.yspd();
	}

	public Action getAction() {
		Action action = this.action;
		this.action = null;
		return action;
	}

	public ActorType getType() {
		return type;
	}

	public boolean isSameTeam(Actor actor) {
		return actor.team() == this.team();
	}

	protected int team() {
		return type.team();
	}

	public Item getCorpse(ItemTypePool itemPool) {
		return new Item(itemPool.getType(type.corpse));
	}

	public void addToHands(Item item) {
		dropInHands();
		inHands = item;
	}

	public void dropInHands() {
		area.addItem(inHands, x, y);
		inHands = null;
	}

	public void grab(Direction dir) {
		dropInHands();
		inHands = area.grab(x + dir.xspd(), y + dir.yspd(), dir);
	}

}
