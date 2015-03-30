
package com.trisse.spacerouge.graphics;

public class Screen {
    
    public static final int tileSize   = 16;
    
    public static int       tileWidth  = 64;
    public static int       tileHeight = 36;
    
    public int              currentTileWidth;
    public int              currentTileHeight;
    
    public Sprite[][]       screen;
    
    public Screen(int tileWidth, int tileHeight) {
        init(tileWidth, tileHeight);
    }
    
    public Screen() {
        init(tileWidth, tileHeight);
    }
    
    private void init(int tileWidth, int tileHeight) {
        currentTileHeight = tileHeight;
        currentTileWidth = tileWidth;
        screen = new Sprite[tileHeight * tileWidth][3];
        
    }
    
    public void draw(Sprite sprite, int x, int y) {
        screen[x + y * tileWidth][0] = sprite;
    }
    
    public void draw(Sprite sprite, int x, int y, int layer) {
        if (x >= 0 && x < tileWidth && y >= 0 && y < tileHeight) {
            int index = x + y * tileWidth;
            screen[index][layer] = sprite;
        }
    }
    
    public void render() {
        for (int y = 0; y < tileHeight; y++) {
            for (int x = 0; x < tileWidth; x++) {
                for (int i = 0; i < screen[x + y * tileWidth].length; i++) {
                    if (screen[x + y * tileWidth][i] != null) {
                        screen[x + y * tileWidth][i].render(x, y);
                    }
                }
            }
        }
    }
    
    public void clear() {
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                screen[i][j] = null;
            }
            
        }
    }
    
    public int getWidth() {
        return tileWidth * tileSize;
    }
    
    public int getHeight() {
        return tileHeight * tileSize;
    }
    
    public void drawString(String str, int x, int y) {
        for (int i = 0; i < str.length(); i++) {
            screen[i + x + y * tileWidth][0] = Sprite.getChar(str.charAt(i));
        }
    }
    
    public void drawString(String str, int x, int y, int layer) {
        for (int i = 0; i < str.length(); i++) {
            screen[i + x + y * tileWidth][layer] = Sprite.getChar(str.charAt(i));
        }
    }
    
}
