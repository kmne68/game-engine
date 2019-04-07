/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
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
    
    
    public static IntBuffer createIntBuffer(int size)
    {
        return BufferUtils.createIntBuffer(size);
    }
    
    
    public static IntBuffer createFlippedBuffer(int... values)
    {
        IntBuffer buffer = createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        
        return buffer;
    }
    
    
    public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
    {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
        
        for(int i = 0; i < vertices.length; i++)
        {
            buffer.put(vertices[i].getPosition().getX());
            buffer.put(vertices[i].getPosition().getY());
            buffer.put(vertices[i].getPosition().getZ());   
            buffer.put(vertices[i].getTextureCoordinate().getX());
            buffer.put(vertices[i].getTextureCoordinate().getY());
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
    
    
    public static String[] removeEmptyStrings(String[] data)
    {
        ArrayList<String> result = new ArrayList<String>();
        
        for(int i = 0; i < data.length; i++)
            if(!data[i].equals(""))
                result.add(data[i]);
        
        String[] res = new String[result.size()];
        result.toArray(res);
        
        return res;
    }
    
    
    public static int[] toIntArray(Integer[] data)
    {
        int[] result = new int[data.length];
        
        for(int i = 0; i < data.length; i++)
            result[i] = data[i].intValue();
        
        return result;
    }
    
}
