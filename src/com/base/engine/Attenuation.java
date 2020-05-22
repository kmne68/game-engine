/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author kmne6
 */
public class Attenuation {
  
  private float constant;
  private float linear;
  private float exponent;
  
  
  public Attenuation(float constant, float linear, float exponent) {
    
    this.constant = constant;
    this.linear = linear;
    this.exponent = exponent;
    
  }
  

  public float getConstant() {
    return constant;
  }

  public void setConstant(float constant) {
    this.constant = constant;
  }

  public float getLinear() {
    return linear;
  }

  public void setLinear(float linear) {
    this.linear = linear;
  }

  public float getExponent() {
    return exponent;
  }

  public void setExponent(float exponent) {
    this.exponent = exponent;
  }
  
}
