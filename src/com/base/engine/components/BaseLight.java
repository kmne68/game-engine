/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.rendering.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Shader;

/**
 *
 * @author kmne6
 */
public class BaseLight extends GameComponent {
  
  private Vector3f color;
  private float intensity;
  private Shader shader;
  
  public BaseLight(Vector3f color, float intensity) {
    
    this.color = color;
    this.intensity = intensity;
    
  }
  
  @Override
  public void addToRenderingEngine(RenderingEngine renderingEngine) {
    
    renderingEngine.addLight(this);
  }
  
  public Shader getShader() {
    
    return shader;
  }
  
  public void setShader(Shader shader) {
    
    this.shader = shader;
  }

  public Vector3f getColor() {
    return color;
  }

  public void setColor(Vector3f color) {
    this.color = color;
  }

  public float getIntensity() {
    return intensity;
  }

  public void setIntensity(float intensity) {
    this.intensity = intensity;
  }
}
