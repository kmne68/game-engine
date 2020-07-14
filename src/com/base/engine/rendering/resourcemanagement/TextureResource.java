/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.resourcemanagement;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;

/**
 *
 * @author kmne6
 */
public class TextureResource {
  
    private int id;
    private int referenceCount;   // for counting meshes for deletion
  
  public TextureResource(int id) {
    
    this.id = id;
    this.referenceCount = 1;
  }
  
  @Override
  protected void finalize() {
    
    glDeleteBuffers(id);
  }
  
  
  public void addReference() {
    
    referenceCount++;    
  }
  
  
  public boolean removeReference() {
    
    referenceCount--;
    return referenceCount == 0;
    
  }

  public int getId() {
    return id;
  }
  
}
