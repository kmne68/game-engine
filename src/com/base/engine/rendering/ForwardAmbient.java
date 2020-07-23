/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

/**
 *
 * @author kmne6
 */
public class ForwardAmbient extends Shader {
  
  private static final ForwardAmbient instance = new ForwardAmbient();

  public static ForwardAmbient getInstance() {
    
    return instance;

  }

  private ForwardAmbient() {

    super("forward-ambient");

  }

  public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine) {
    
    super.updateUniforms(transform, material, renderingEngine);

  }

  
}
