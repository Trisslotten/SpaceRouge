package com.trisse.spacerouge.entities.actor;

import com.trisse.spacerouge.*;
import com.trisse.spacerouge.action.*;
import com.trisse.spacerouge.entities.*;
import com.trisse.spacerouge.entities.item.*;
import com.trisse.spacerouge.entities.item.types.*;
import com.trisse.spacerouge.entities.tile.*;
import com.trisse.spacerouge.graphics.*;
import com.trisse.spacerouge.level.*;

public class Actor extends Entity {

	protected Action action;

	protected ActorType type;

	protected Item inHands;
	
	protected Stomach stomach;

	public boolean isPlayer = false;

	protected int health;

	private Map map;

	protected int damagedCounter = 0;

	public Actor(ActorType type, int x, int y, Map map) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.map = map;

		health = type.getHealth();
		
		
		stomach = new Stomach();
	}

	public void think() {
		type.think(this);
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void walk(Direction dir) {
		setNextAction(new WalkAction(this, map, dir));
	}

	public void tick() {
		stomach.tick(this);
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
		map.addNewItem(inHands, x, y);
		removeItem();
	}

	public boolean grab(Direction dir) {
		Item item = map.getAndRemoveItem(x, y, 0);
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

	public void eat(Food food) {
		stomach.eat(food);
	}

	public Tile getOccupiedTile() {
		return map.getTile(x,y);
	}

	public void kill(String string) {
		
	}

	public void faint(String string) {
		
	}

}
