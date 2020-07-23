/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.resourcemanagement.MappedValues;
import java.util.HashMap;

/**
 *
 * @author kmne6
 */
public class Material extends MappedValues {
  
  private static final int DEFAULT_SPECULAR_INTENSITY = 2;
  private static final int DEFAULT_SPECULAR_EXPONENT = 32;
  
  private HashMap<String, Texture> textureHashMap;
//  private HashMap<String, Vector3f> vector3fHashMap;
//  private HashMap<String, Float> floatHashMap;

  
  public Material() {
    
    super();
    textureHashMap = new HashMap<String, Texture>();
   
  }

  public Texture getTexture(String name) {
    
    Texture result = textureHashMap.get(name);
    if(result != null)
      return result;
    else
      return new Texture("test.png");
  }

  public void addTexture(String name, Texture texture) {
    textureHashMap.put(name, texture);
  }
  
}
