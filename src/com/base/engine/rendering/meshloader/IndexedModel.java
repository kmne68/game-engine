/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.meshloader;

import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import java.util.ArrayList;

/**
 *
 * @author kmne6
 */
public class IndexedModel {
  
  private ArrayList<Vector3f> positions;
  private ArrayList<Vector2f> textureCoordinates;
  private ArrayList<Vector3f> normals;
  private ArrayList<Integer>  indices;
  
  
  public IndexedModel() {
    
    positions = new ArrayList<Vector3f>();
    textureCoordinates = new ArrayList<Vector2f>();
    normals = new ArrayList<Vector3f>();
    indices = new ArrayList<Integer>();    
    
  }

  public ArrayList<Vector3f> getPositions() {
    return positions;
  }

  public ArrayList<Vector2f> getTextureCoordinates() {
    return textureCoordinates;
  }

  public ArrayList<Vector3f> getNormals() {
    return normals;
  }

  public ArrayList<Integer> getIndices() {
    return indices;
  }
  
  
}
