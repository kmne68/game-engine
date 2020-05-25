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
public class SpotLight {
  
  private PointLight pointLight;
  private Vector3f direction;
  private float cutoff;

  public SpotLight(PointLight pointLight, Vector3f direction, float cutoff) {
    this.pointLight = pointLight;
    this.direction = direction.normalize();
    this.cutoff = cutoff;
  }

  public PointLight getPointLight() {
    return pointLight;
  }

  public void setPointLight(PointLight pointLight) {
    this.pointLight = pointLight;
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