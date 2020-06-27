/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.core.Vector3f;
import java.util.HashMap;

/**
 *
 * @author kmne6
 */
public class Material {
  
  private static final int DEFAULT_SPECULAR_INTENSITY = 2;
  private static final int DEFAULT_SPECULAR_EXPONENT = 32;
  
  private HashMap<String, Texture> textureHashMap;
  private HashMap<String, Vector3f> vector3fHashMap;
  private HashMap<String, Float> floatHashMap;

  
  public Material() {
    
    textureHashMap = new HashMap<String, Texture>();
    vector3fHashMap = new HashMap<String, Vector3f>();
    floatHashMap = new HashMap<String, Float>();
   
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

  
  public Vector3f getVector3f(String name) {
    
    Vector3f result = vector3fHashMap.get(name);
    if(result != null)
      return result;
    else
      return new Vector3f(0, 0, 0);
    
  }

  public void addVector3f(String name, Vector3f vector3f) {
    vector3fHashMap.put(name, vector3f);
  }

  
  public float getFloat(String name) {
    
    Float result = floatHashMap.get(name);
    if(result != null)
      return result;
    else
      return 0;
  }

  public void addFloat(String name, float floatValue) {
    floatHashMap.put(name, floatValue);
  }
  
}
