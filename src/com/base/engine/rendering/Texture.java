/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import java.io.File;
import java.io.FileInputStream;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author kmne6
 */
public class Texture {
    
    private int id;
    
    
    public Texture(String fileName) {
      
      this(loadTexture(fileName));
    }
    
    public Texture(int id)
    {
        this.id = id;
    }
    
    public void bind()
    {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public int getID()
    {
        return id;
    }
    
    private static int loadTexture(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];
        
        try
        {
            int id = TextureLoader.getTexture(ext, new FileInputStream(new File("./res/textures/" + fileName))).getTextureID();
            
            return id;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        
        return 0;
    }
    
}
