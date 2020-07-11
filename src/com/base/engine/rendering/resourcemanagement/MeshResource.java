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
  
  public MeshResource() {
    
    vbo = glGenBuffers();
    ibo = glGenBuffers();
  }
  
  @Override
  protected void finalize() {
    
    glDeleteBuffers(vbo);
    glDeleteBuffers(ibo);
  }

  public int getVbo() {
    return vbo;
  }

  public int getIbo() {
    return ibo;
  }
  
}
