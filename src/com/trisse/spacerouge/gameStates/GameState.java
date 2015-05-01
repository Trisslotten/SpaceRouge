package com.trisse.spacerouge.gameStates;

import com.trisse.spacerouge.collections.*;
import com.trisse.spacerouge.graphics.*;

public abstract class GameState {

	public Sprites sprites;

	public EntityTypePool entityTypePool;

	public GameState(Sprites sprites, EntityTypePool entityTypePool) {
		this.sprites = sprites;
		this.entityTypePool = entityTypePool;
	}

	public abstract void render(Screen screen);

}
