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
public class BasicShader extends Shader {

  private static final BasicShader instance = new BasicShader();

  public static BasicShader getInstance() {
    
    return instance;

  }

  private BasicShader() {

    super();
    
    addVertexShaderFromFile("basicVertex.vs");    
    addFragmentShaderFromFile("basicFragment.fs");
    compileShader();

    addUniform("transform");
    addUniform("color");

  }

  public void updateUniforms(Transform transform, Material material) {
    
    Matrix4f worldMatrix = transform.getTransformation();
    Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().multiplyMatrix(worldMatrix);
    material.getTexture().bind();

    setUniform("transform", projectedMatrix);
    setUniform("color", material.getColor());
  }

}