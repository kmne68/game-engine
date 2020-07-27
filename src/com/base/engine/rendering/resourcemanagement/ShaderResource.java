/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.resourcemanagement;

import java.util.ArrayList;
import java.util.HashMap;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.glCreateProgram;

/**
 *
 * @author kmne6
 */
public class ShaderResource {
  
  private int program;
  private HashMap<String, Integer> uniforms;
  private ArrayList<String> uniformNames;
  private ArrayList<String> uniformTypes;
  private int refCount;
  
  public ShaderResource() {
    
    this.program = glCreateProgram();
    this.refCount = 1;
    
    if(program == 0) {
      System.err.println("Shader creation failed: could not find valid memory location in constructor.");
      System.exit(1);
    }
    
    uniforms = new HashMap<String, Integer>();
    uniformNames = new ArrayList<String>();
    uniformTypes = new ArrayList<String>();    
  }
  
  @Override
  protected void finalize() {
    glDeleteBuffers(program);
  }
  
  public void addReference() {
    refCount++;
  }
  
  public boolean removeReference() {
    refCount--;
    return refCount == 0;
  }
  
  public int getProgram() {
    return program;
  }
  
  public HashMap<String, Integer> getUniforms() {
    return uniforms;
  }
  
  public ArrayList<String> getUniformNames() {
    return uniformNames;
  }
  
  public ArrayList<String> getUniformTypes() {
    return uniformTypes;
  }
  
  
  
}
