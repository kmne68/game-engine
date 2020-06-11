/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardPoint;

/**
 *
 * @author kmne6
 */
public class PointLight extends BaseLight {

  private BaseLight baseLight;
  private Vector3f position;
  private Vector3f attenuation;
  private float range;


  public PointLight(Vector3f color, float intensity, Vector3f attenuation, Vector3f position, float range) {
  
    super(color, intensity);
    this.position = position;
    this.range    = range;
    this.attenuation = attenuation;
    
    setShader(ForwardPoint.getInstance());
  }

  public BaseLight getBaseLight() {
    return baseLight;
  }

  public void setBaseLight(BaseLight baseLight) {
    this.baseLight = baseLight;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public float getRange() {
    return range;
  }

  public void setRange(float range) {
    this.range = range;
  }

  public float getConstant() {
    return attenuation.getX();
  }

  public void setConstant(float constant) {
    this.attenuation.setX(constant);
  }

  public float getLinear() {
    return attenuation.getY();
  }

  public void setLinear(float linear) {
    this.attenuation.setY(linear);
  }

  public float getExponent() {
    return attenuation.getZ();
  }

  public void setExponent(float exponent) {
    this.attenuation.setZ(exponent);
  }

  
}
