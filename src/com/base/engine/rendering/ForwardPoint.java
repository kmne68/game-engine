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

    super("forward-point");

  }

  public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine) {

    Matrix4f worldMatrix = transform.getTransformation();
    Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().multiplyMatrix(worldMatrix);
    material.getTexture("diffuse").bind();

    setUniform("model", worldMatrix);
    setUniform("MVP", projectedMatrix);

    setUniformf("specularIntensity", material.getFloat("specularIntensity")); //getSpecularIntensity());
    setUniformf("specularPower", material.getFloat("specularPower")); //getSpecularPower());

    setUniform("eyePosition", renderingEngine.getMainCamera().getTransform().getTransformPosition());
    setUniformPointLight("pointLight", (PointLight) renderingEngine.getActiveLight());

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
    setUniform(uniformName + ".position", pointLight.getTransform().getTransformPosition());
    setUniformf(uniformName + ".range", pointLight.getRange());
  }
  
}
