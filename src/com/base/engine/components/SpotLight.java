/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Shader;

/**
 *
 * @author kmne6
 */
public class SpotLight extends PointLight {

  private float cutoff;

  public SpotLight(Vector3f color, float intensity, Vector3f attenuation, float cutoff) {
    // attenuation comprises constant, linear and exponent properties
    
    super(color, intensity, attenuation);
    this.cutoff = cutoff;
    
    setShader(new Shader("forward-spot"));
  }

  public Vector3f getDirection() {
    
    return getTransform().getTransformRotation().getForward();
  }


  public float getCutoff() {
    return cutoff;
  }

  public void setCutoff(float cutoff) {
    this.cutoff = cutoff;
  } 
  
}
