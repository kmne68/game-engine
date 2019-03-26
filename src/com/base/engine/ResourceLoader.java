/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author kmne68
 */
public class ResourceLoader {
    
    
    
    public static Mesh loadMesh(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length -1];
        
        if(!ext.equals("obj"))
        {
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }
        
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        BufferedReader meshReader = null;
        
        try
        {
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;
            
            while((line = meshReader.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                tokens = BufferUtil.removeEmptyStrings(tokens);
                
                if(tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                else if(tokens[0].equals("v"))
                {
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
                                                        Float.valueOf(tokens[2]),
                                                        Float.valueOf(tokens[3]))));
                }
                else if(tokens[0].equals("f")) 
                {
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    
                    if(tokens.length > 4)
                    {
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            
            meshReader.close();
            
            Mesh res = new Mesh();
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);
            
            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);
            
            res.addVertices(vertexData, BufferUtil.toIntArray(indexData));
            
            return res;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }        

        
        
        return null;
    }
    
    
    
    public static String loadShader(String fileName)
    {
        StringBuilder shaderSource = new StringBuilder();        
        BufferedReader shaderReader = null;
        
        try
        {
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            String line;
            
            while((line = shaderReader.readLine()) != null)
            {
                shaderSource.append(line).append("\n");
            }
            
            shaderReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }        
        
        return shaderSource.toString();
    }
    
}