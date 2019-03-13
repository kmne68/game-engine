/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author kmne68
 */
public class ResourceLoader {
    
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
