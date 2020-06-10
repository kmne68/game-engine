/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.components.PointLight;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardSpot;

/**
 *
 * @author kmne6
 */
public class SpotLight extends PointLight {

  private Vector3f direction;
  private float cutoff;

  public SpotLight(Vector3f color, float intensity, float constant, float linear, float exponent, Vector3f position, float range, Vector3f direction, float cutoff) {
    
    super(color, intensity, constant, linear, exponent, position, range);
    this.direction = direction.normalize();
    this.cutoff = cutoff;
    
    setShader(ForwardSpot.getInstance());
  }

  public Vector3f getDirection() {
    return direction;
  }

  public void setDirection(Vector3f direction) {
    this.direction = direction.normalize();
  }

  public float getCutoff() {
    return cutoff;
  }

  public void setCutoff(float cutoff) {
    this.cutoff = cutoff;
  } 
  
}
