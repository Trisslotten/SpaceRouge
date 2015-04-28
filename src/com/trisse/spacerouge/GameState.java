package com.trisse.spacerouge;

import com.trisse.spacerouge.graphics.Screen;

public abstract class GameState {
	
	public abstract void think(Game game);
	
	public abstract void tick();
	
	public abstract void render(Screen screen);

}
