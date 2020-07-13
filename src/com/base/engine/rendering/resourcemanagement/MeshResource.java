/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.resourcemanagement;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

/**
 *
 * @author kmne6
 */
public class MeshResource {
  
    private int vbo;    // A pointer (handle)
    private int ibo;    // Described as an array of integers on the graphics card
    private int size;
    private int referenceCount;   // for counting meshes for deletion
  
  public MeshResource(int size) {
    
    vbo = glGenBuffers();
    ibo = glGenBuffers();
    this.size = size;
    this.referenceCount = 1;
  }
  
  @Override
  protected void finalize() {
    
    glDeleteBuffers(vbo);
    glDeleteBuffers(ibo);
  }
  
  
  public void addReference() {
    
    referenceCount++;    
  }
  
  
  public boolean removeReference() {
    
    referenceCount--;
    return referenceCount == 0;
    
  }

  public int getVbo() {
    return vbo;
  }

  public int getIbo() {
    return ibo;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
  
}
