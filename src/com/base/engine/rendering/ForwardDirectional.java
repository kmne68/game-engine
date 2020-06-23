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

    super();
    
    addVertexShaderFromFile("forward-directional.vs");    
    addFragmentShaderFromFile("forward-directional.fs");
    
    setAttributeLocation("position", 0);
    setAttributeLocation("textureCoordinate", 1);
    setAttributeLocation("normal", 2);
    
    compileShader();

    addUniform("model");
    addUniform("MVP");
    
    addUniform("specularIntensity");
    addUniform("specularPower");
    addUniform("eyePosition");
    
    addUniform("directionalLight.base.color");
    addUniform("directionalLight.base.intensity");
    addUniform("directionalLight.direction");

  }

  public void updateUniforms(Transform transform, Material material) {
    
    Matrix4f worldMatrix = transform.getTransformation();
    Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().multiplyMatrix(worldMatrix);
    material.getTexture().bind();

    setUniform("model", worldMatrix);
    setUniform("MVP", projectedMatrix);
    
    setUniformf("specularIntensity", material.getSpecularIntensity() );
    setUniformf("specularPower", material.getSpecularPower());
    
    setUniform("eyePosition", getRenderingEngine().getMainCamera().getTransform().getTransformPosition() );
    setUniformDirectionalLight("directionalLight", (DirectionalLight) getRenderingEngine().getActiveLight());
    
//    setUniform("MVP", projectedMatrix);
//    setUniform("ambientIntensity", getRenderingEngine().getAmbientLight());
  }
  
  public void setUniformBaseLight(String uniformName, BaseLight baseLight) {
    
    setUniform( uniformName + ".color", baseLight.getColor() );
    setUniformf( uniformName + ".intensity", baseLight.getIntensity() );
  }
  
  public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight) {
    
    setUniformBaseLight( uniformName + ".base", directionalLight );
    setUniform( uniformName + ".direction", directionalLight.getDirection() );
  }  
}
