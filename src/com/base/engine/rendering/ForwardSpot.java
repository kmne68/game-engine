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

    super("forward-spot");

  }

  public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine) {

    Matrix4f worldMatrix = transform.getTransformation();
    Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().multiplyMatrix(worldMatrix);
    material.getTexture("diffuse").bind();

    setUniform("model", worldMatrix);
    setUniform("MVP", projectedMatrix);

    setUniformf("specularIntensity", material.getFloat("specularIntensity"));
    setUniformf("specularPower", material.getFloat("specularPower"));

    setUniform("eyePosition", renderingEngine.getMainCamera().getTransform().getTransformPosition());
    setUniformSpotLight("spotLight", (SpotLight) renderingEngine.getActiveLight());

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

  public void setUniformSpotLight(String uniformName, SpotLight spotLight) {

    setUniformPointLight(uniformName + ".pointLight", spotLight);
    setUniform(uniformName + ".direction", spotLight.getDirection());
    setUniformf(uniformName + ".cutoff", spotLight.getCutoff());
  }

}
