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
public class PointLight {
  
  private BaseLight baseLight;
  private Attenuation attenuation;
  private Vector3f position;

  public PointLight(BaseLight baseLight, Attenuation attenuation, Vector3f position) {
    this.baseLight = baseLight;
    this.attenuation = attenuation;
    this.position = position;
  }

  public BaseLight getBaseLight() {
    return baseLight;
  }

  public void setBaseLight(BaseLight baseLight) {
    this.baseLight = baseLight;
  }

  public Attenuation getAttenuation() {
    return attenuation;
  }

  public void setAttenuation(Attenuation attenuation) {
    this.attenuation = attenuation;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }  
  
}
