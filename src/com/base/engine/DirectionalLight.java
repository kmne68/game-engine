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
public class DirectionalLight {
  
  private BaseLight base;
  private Vector3f direction;

  public DirectionalLight(BaseLight base, Vector3f direction) {
    this.base = base;
    this.direction = direction.normalize();
  }

  public BaseLight getBase() {
    return base;
  }

  public void setBase(BaseLight base) {
    this.base = base;
  }

  public Vector3f getDirection() {
    return direction;
  }

  public void setDirection(Vector3f direction) {
    this.direction = direction;
  }
  
}
