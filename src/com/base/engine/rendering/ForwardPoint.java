/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.PointLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

/**
 *
 * @author kmne6
 */
public class ForwardPoint extends Shader {

  private static final ForwardPoint instance = new ForwardPoint();

  public static ForwardPoint getInstance() {

    return instance;

  }

  private ForwardPoint() {

    super();

    addVertexShaderFromFile("forward-point.vs");
    addFragmentShaderFromFile("forward-point.fs");

    setAttributeLocation("position", 0);
    setAttributeLocation("textureCoordinate", 1);
    setAttributeLocation("normal", 2);

    compileShader();

    addUniform("model");
    addUniform("MVP");

    addUniform("specularIntensity");
    addUniform("specularPower");
    addUniform("eyePosition");

    addUniform("pointLight.base.color");
    addUniform("pointLight.base.intensity");
    addUniform("pointLight.attenuation.constant");
    addUniform("pointLight.attenuation.linear");
    addUniform("pointLight.attenuation.exponent");
    addUniform("pointLight.position");
    addUniform("pointLight.range");

  }

  public void updateUniforms(Transform transform, Material material) {

    Matrix4f worldMatrix = transform.getTransformation();
    Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().multiplyMatrix(worldMatrix);
    material.getTexture().bind();

    setUniform("model", worldMatrix);
    setUniform("MVP", projectedMatrix);

    setUniformf("specularIntensity", material.getSpecularIntensity());
    setUniformf("specularPower", material.getSpecularPower());

    setUniform("eyePosition", getRenderingEngine().getMainCamera().getPosition());
    setUniformPointLight("pointLight", (PointLight) getRenderingEngine().getActiveLight());

//    setUniform("MVP", projectedMatrix);
//    setUniform("ambientIntensity", getRenderingEngine().getAmbientLight());
  }

  public void setUniformBaseLight(String uniformName, BaseLight baseLight) {

    setUniform(uniformName + ".color", baseLight.getColor());
    setUniformf(uniformName + ".intensity", baseLight.getIntensity());
  }

  public void setUniformPointLight(String uniformName, PointLight pointLight) {
    
    setUniformBaseLight(uniformName + ".base", pointLight);
    setUniformf(uniformName + ".attenuation.constant", pointLight.getConstant());
    setUniformf(uniformName + ".attenuation.linear", pointLight.getLinear());
    setUniformf(uniformName + ".attenuation.exponent", pointLight.getExponent());
    setUniform(uniformName + ".position", pointLight.getTransform().getPosition());
    setUniformf(uniformName + ".range", pointLight.getRange());
  }
  
}
