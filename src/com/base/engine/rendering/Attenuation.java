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
public class Attenuation extends Vector3f {
  
  public Attenuation(float constant, float linear, float exponent) {
    super(constant, linear, exponent);
  }
  
  public float getConstant() {
    
    return getX();
  }
  
  public float getLinear() {
    
    return getY();
  }
  
  public float getExponent() {
    
    return getZ();
  }
  
  
  
}
