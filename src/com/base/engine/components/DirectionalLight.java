/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.rendering.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Shader;

/**
 *
 * @author kmne6
 */
public class DirectionalLight extends BaseLight {
  
  private BaseLight base;

  public DirectionalLight(Vector3f color, float intensity) {
    
    super(color, intensity);
    
    setShader(new Shader("forward-directional"));
  }

  public Vector3f getDirection() {
    return getTransform().getTransformRotation().getForward();
  }

}
