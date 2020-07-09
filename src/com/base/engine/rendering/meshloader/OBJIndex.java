/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.meshloader;

/**
 *
 * @author kmne6
 */
public class OBJIndex {
  
  public int vertexIndex;
  public int textureCoordinateIndex;
  public int normalIndex;
  
  
  @Override
  public boolean equals(Object object) {
    
    OBJIndex index = (OBJIndex) object;
    
    return vertexIndex == index.vertexIndex
            && textureCoordinateIndex == index.textureCoordinateIndex
            && normalIndex == index.normalIndex;
  }
  
  @Override
  public int hashCode() {
    
    final int BASE = 17;
    final int MULTIPLIER = 31;
    
    int result = BASE;
    
    result = MULTIPLIER * result + vertexIndex;
    result = MULTIPLIER * result + textureCoordinateIndex;
    result = MULTIPLIER * result + normalIndex;
    
    return result;
    
  }
  
}
