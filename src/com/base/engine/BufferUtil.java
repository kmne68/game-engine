/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

/**
 *
 * @author kmne68
 */
public class BufferUtil {
    
    public static FloatBuffer createFloatBuffer(int size)
    {
        return BufferUtils.createFloatBuffer(size);
    }
    
    public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
    {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
        
        for(int i = 0; i < vertices.length; i++)
        {
            buffer.put(vertices[i].getPosition().getX());
            buffer.put(vertices[i].getPosition().getY());
            buffer.put(vertices[i].getPosition().getZ());            
        }
        
        buffer.flip();
        
        return buffer;
    }
    
    
    public static FloatBuffer createFlippedBuffer(Matrix4f value)
    {
        FloatBuffer buffer = createFloatBuffer(4 * 4);
        
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                buffer.put(value.get(i, j));
        
        buffer.flip();
        
        return buffer;
    }
}
