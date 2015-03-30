
package com.trisse.spacerouge.graphics;

import static org.lwjgl.opengl.GL11.GL_CLAMP;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL12;

public class Texture {
    
    public final int           target          = GL_TEXTURE_2D;
    public final int           id;
    public final int           width;
    public final int           height;
    
    public static final int    LINEAR          = GL_LINEAR;
    public static final int    NEAREST         = GL_NEAREST;
    
    public static final int    CLAMP           = GL_CLAMP;
    public static final int    CLAMP_TO_EDGE   = GL12.GL_CLAMP_TO_EDGE;
    public static final int    REPEAT          = GL_REPEAT;
    
    public static final String defaultLocation = "res/missing.png";
    
    public Texture(String path) {
        this(path, NEAREST, CLAMP_TO_EDGE);
    }
    
    public Texture(String path, int filter) {
        this(path, filter, CLAMP_TO_EDGE);
    }
    
    public Texture(String path, int filter, int wrap) {
        InputStream input = null;
        try {
            input = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                input = new FileInputStream(defaultLocation);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        
        PNGDecoder dec = null;
        ByteBuffer buf = null;
        try {
            dec = new PNGDecoder(input);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        width = dec.getWidth();
        height = dec.getHeight();
        
        // we will decode to RGBA format, i.e. 4 components or "bytes per pixel"
        final int bpp = 4;
        
        // create a new byte buffer which will hold our pixel data
        buf = BufferUtils.createByteBuffer(bpp * width * height);
        
        // decode the image into the byte buffer, in RGBA format
        try {
            dec.decode(buf, width * bpp, PNGDecoder.Format.RGBA);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // flip the buffer into "read mode" for OpenGL
        buf.flip();
        
        // Generally a good idea to enable texturing first
        glEnable(GL_TEXTURE_2D);
        
        // generate a texture handle or unique ID for this texture
        id = glGenTextures();
        
        // bind the texture
        glBindTexture(GL_TEXTURE_2D, id);
        
        // use an alignment of 1 to be safe
        // this tells OpenGL how to unpack the RGBA bytes we will specify
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        
        // set up our texture parameters
        // Setup filtering, i.e. how OpenGL will interpolate the pixels when scaling up or down
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        
        // Setup wrap mode, i.e. how OpenGL will handle pixels outside of the expected range
        // Note: GL_CLAMP_TO_EDGE is part of GL12
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        
        // upload our ByteBuffer to GL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
    }
    
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public static void debugTexture(Texture tex, float x, float y, float width, float height) {
        // usually glOrtho would not be included in our game loop
        // however, since it's deprecated, let's keep it inside of this debug function which we will remove later
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glEnable(GL_TEXTURE_2D); // likely redundant; will be removed upon migration to "modern GL"
        // bind the texture before rendering it
        tex.bind();
        
        // setup our texture coordinates
        // (u,v) is another common way of writing (s,t)
        float u = 0f;
        float v = 0f;
        float u2 = 1f;
        float v2 = 1f;
        
        // immediate mode is deprecated -- we are only using it for quick debugging
        glColor4f(1f, 1f, 1f, 1f);
        glBegin(GL_QUADS);
        glTexCoord2f(u, v);
        glVertex2f(x, y);
        glTexCoord2f(u, v2);
        glVertex2f(x, y + height);
        glTexCoord2f(u2, v2);
        glVertex2f(x + width, y + height);
        glTexCoord2f(u2, v);
        glVertex2f(x + width, y);
        glEnd();
    }
    
}
