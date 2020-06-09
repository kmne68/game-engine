/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardDirectional;

/**
 *
 * @author kmne6
 */
public class DirectionalLight extends BaseLight {
  
  private BaseLight base;
  private Vector3f direction;

  public DirectionalLight(Vector3f color, float intensity, Vector3f direction) {
    
    super(color, intensity);
    this.direction = direction.normalize();
    
    setShader(ForwardDirectional.getInstance());
  }

  public Vector3f getDirection() {
    return direction;
  }

  public void setDirection(Vector3f direction) {
    this.direction = direction.normalize();
  }
  
}
