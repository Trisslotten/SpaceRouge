package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.level.*;
import com.trisse.spacerouge.util.*;

public class Actor extends Entity {

	protected Action action;

	protected ActorType type;

	protected Item inHands;

	public boolean isPlayer = false;

	protected int health;

	private Area area;

	protected int damagedCounter = 0;

	public Actor(ActorType type, int x, int y, Area area) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.area = area;

		health = type.getHealth();
	}

	public void init() {
		setTransparent(true);
	}

	public void think() {
		type.think(this);
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
		if (inHands != null) {
			enemy.getDamaged(inHands.getType().getDamage());
		} else {
			enemy.getDamaged(type.getDamage());
		}

	}

	public void setNextAction(Action action) {
		this.action = action;
	}

	public void render(Screen screen, int xoffset, int yoffset) {
		if (isVisible)
			if (damagedCounter > 0) {
				screen.draw("damaged", x - xoffset, y - yoffset, Levels.ACTOR);
				damagedCounter--;
			} else {
				screen.draw(type.getSprite(), x - xoffset, y - yoffset, Levels.ACTOR);
			}
	}

	public boolean somethingInHands() {
		return inHands != null;
	}

	public void getDamaged(int damage) {
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
		removeItem();
	}

	public boolean grab(Direction dir) {
		Item item = area.getAndRemoveItem(x + dir.xspd(), y + dir.yspd(), dir);
		if (item != null) {
			dropInHands();
			inHands = item;
			return true;
		}
		return false;
	}

	public Item getItem() {
		return inHands;
	}

	public void heal(int healing) {
		health += healing;
		if (health > type.getHealth()) {
			health = type.getHealth();
		}
	}

	public boolean activateItem() {
		if (inHands != null) {
			if (!inHands.activate(this)) {
				return false;
			}
			if (inHands.getsUsed()) {
				removeItem();
			}
			return true;
		} else {
			return false;
		}
	}

	private void removeItem() {
		inHands = null;
	}

}
