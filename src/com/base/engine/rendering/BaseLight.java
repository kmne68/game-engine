/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.core.Vector3f;

/**
 *
 * @author kmne6
 */
public class BaseLight {
  
  private Vector3f color;
  private float intensity;
  
  public BaseLight(Vector3f color, float intensity) {
    
    this.color = color;
    this.intensity = intensity;
    
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
