/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author kmne6
 * 
 * PhongShader implements the Phong lighting model
 * 
 */
public class PhongShader extends Shader {

  private static final PhongShader instance = new PhongShader();

  public static PhongShader getInstance() {
    
    return instance;

  }
  
  private static Vector3f ambientLight;



  private PhongShader() {

    super();
    
    addVertexShader(ResourceLoader.loadShader("phongVertex.vs"));    
    addFragmentShader(ResourceLoader.loadShader("phongFragment.fs"));
    compileShader();

    addUniform("transform");
    addUniform("baseColor");
    addUniform("ambientLight");

  }

  public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
    
    if (material.getTexture() != null) {
      material.getTexture().bind();
    } else {
      RenderUtil.unbindTextures();
    }

    setUniform("transform", projectedMatrix);
    setUniform("baseColor", material.getColor());
    setUniform("ambientLight", ambientLight);
  }
  
  
  public static Vector3f getAmbientLight() {
    return ambientLight;
  }

  public static void setAmbientLight(Vector3f ambientLight) {
    ambientLight = ambientLight;
  }

}

