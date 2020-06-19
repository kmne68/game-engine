/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.components.SpotLight;
import com.base.engine.components.BaseLight;
import com.base.engine.components.PointLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

/**
 *
 * @author kmne6
 */
public class ForwardSpot extends Shader {

  private static final ForwardSpot instance = new ForwardSpot();

  public static ForwardSpot getInstance() {

    return instance;

  }

  private ForwardSpot() {

    super();

    addVertexShaderFromFile("forward-spot.vs");
    addFragmentShaderFromFile("forward-spot.fs");

    setAttributeLocation("position", 0);
    setAttributeLocation("textureCoordinate", 1);
    setAttributeLocation("normal", 2);

    compileShader();

    addUniform("model");
    addUniform("MVP");

    addUniform("specularIntensity");
    addUniform("specularPower");
    addUniform("eyePosition");

    addUniform("spotLight.pointLight.base.color");
    addUniform("spotLight.pointLight.base.intensity");
    addUniform("spotLight.pointLight.attenuation.constant");
    addUniform("spotLight.pointLight.attenuation.linear");
    addUniform("spotLight.pointLight.attenuation.exponent");
    addUniform("spotLight.pointLight.position");
    addUniform("spotLight.pointLight.range");
    addUniform("spotLight.direction");
    addUniform("spotLight.cutoff");

  }

  public void updateUniforms(Transform transform, Material material) {

    Matrix4f worldMatrix = transform.getTransformation();
    Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().multiplyMatrix(worldMatrix);
    material.getTexture().bind();

    setUniform("model", worldMatrix);
    setUniform("MVP", projectedMatrix);

    setUniformf("specularIntensity", material.getSpecularIntensity());
    setUniformf("specularPower", material.getSpecularPower());

    setUniform("eyePosition", getRenderingEngine().getMainCamera().getTransform().getPosition());
    setUniformSpotLight("spotLight", (SpotLight) getRenderingEngine().getActiveLight());

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

  public void setUniformSpotLight(String uniformName, SpotLight spotLight) {

    setUniformPointLight(uniformName + ".pointLight", spotLight);
    setUniform(uniformName + ".direction", spotLight.getDirection());
    setUniformf(uniformName + ".cutoff", spotLight.getCutoff());
  }

}
