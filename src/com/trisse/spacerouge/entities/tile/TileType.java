package com.trisse.spacerouge.entities.tile;

import java.io.Serializable;

import com.trisse.spacerouge.entities.EntityType;
import com.trisse.spacerouge.graphics.Sprite;

public class TileType extends EntityType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159665015474304151L;

	protected boolean isWall;

	protected Sprite sprite;

	
	
}
