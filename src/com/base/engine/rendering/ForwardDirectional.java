/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.DirectionalLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

/**
 *
 * @author kmne6
 */
public class ForwardDirectional extends Shader {
  
    private static final ForwardDirectional instance = new ForwardDirectional();

  public static ForwardDirectional getInstance() {
    
    return instance;

  }

  private ForwardDirectional() {

    super("forward-directional");

  }

  public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine) {
    
    super.updateUniforms(transform, material, renderingEngine);
    
//    Matrix4f worldMatrix = transform.getTransformation();
//    Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().multiplyMatrix(worldMatrix);
//    material.getTexture("diffuse").bind();
//
//    setUniform("model", worldMatrix);
//    setUniform("MVP", projectedMatrix);
//    
//    setUniformf("specularIntensity", material.getFloat("specularIntensity") );
//    setUniformf("specularPower", material.getFloat("specularPower"));
//    
//    setUniform("eyePosition", renderingEngine.getMainCamera().getTransform().getTransformPosition() );
//    setUniformDirectionalLight("directionalLight", (DirectionalLight) renderingEngine.getActiveLight());
  }
  
}
