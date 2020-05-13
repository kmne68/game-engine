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
public class BasicShader extends Shader {

  private static final BasicShader instance = new BasicShader();

  public static BasicShader getInstance() {
    
    return instance;

  }

  private BasicShader() {

    super();
    System.out.println("*** BasicShader() after super() ***");
    
    addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
    System.out.println("*** BasicShader() after addVertexShader() call ***");
    
    addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
    System.out.println("*** BasicShader() after addFragmentShader() call ***");
    compileShader();

    addUniform("transform");
    addUniform("color");

  }

  public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
    
    if (material.getTexture() != null) {
      material.getTexture().bind();
    } else {
      RenderUtil.unbindTextures();
    }

    setUniform("transform", projectedMatrix);
    setUniform("color", material.getColor());
  }

}
