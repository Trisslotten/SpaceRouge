
package com.trisse.spacerouge.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;

import org.lwjgl.opengl.*;

public class SpriteSheet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6395985245623898035L;

	public static final Texture spriteSheetTexture = new Texture("res/spritesheet.png");
    
    public Texture texture;
    
    public int     sheetWidth, sheetHeight;
    
    public int     spriteSize;
    
    public double  s, t;
    
    public SpriteSheet(int spriteSize) {
    	init(spriteSheetTexture, spriteSize);
    }
    
    public SpriteSheet(Texture texture, int spriteSize) {
        init(texture,spriteSize);
    }
    
    public void init(Texture texture, int spriteSize) {
    	this.spriteSize = spriteSize;
        sheetWidth = texture.width / spriteSize;
        sheetHeight = texture.height / spriteSize;
        
        this.texture = texture;
        for (int i = 0; i <= texture.width; i++) {
            double a = Math.pow(2, i + 1);
            if (a >= texture.width) {
                s = Math.round(texture.width / a);
                break;
            }
        }
        for (int i = 0; i * i <= texture.height; i++) {
            double a = Math.pow(2, i + 1);
            if (a >= texture.height) {
                t = Math.round(texture.height / a);
                break;
            }
        }
        s /= ((double) sheetWidth);
        t /= ((double) sheetHeight);
    }
    
    
    
    public void bindSheet() {
        texture.bind();
    }
    
    public void render(int x, int y, double xpos, double ypos) {
        double width = spriteSize;
        double height = spriteSize;
        xpos *= spriteSize;
        ypos *= spriteSize;
        
        double prevS = ((double) x) * this.s;
        double prevT = ((double) y) * this.t;
        
        glBegin(GL_QUADS);
        {
            GL11.glTexCoord2d(prevS, prevT);
            GL11.glVertex2d(xpos, ypos);
            
            GL11.glTexCoord2d(s + prevS, prevT);
            GL11.glVertex2d(xpos + width, ypos);
            
            GL11.glTexCoord2d(s + prevS, t + prevT);
            GL11.glVertex2d(xpos + width, ypos + height);
            
            GL11.glTexCoord2d(prevS, t + prevT);
            GL11.glVertex2d(xpos, ypos + height);
        }
        glEnd();
    }
}
