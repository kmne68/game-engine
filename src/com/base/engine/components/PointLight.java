/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Shader;

/**
 *
 * @author kmne6
 */
public class PointLight extends BaseLight {

  private static final int COLOR_DEPTH = 256;
  
  private Attenuation attenuation;
  private float range;


  public PointLight(Vector3f color, float intensity, Attenuation attenuation) {
  
    super(color, intensity);
    this.attenuation = attenuation;
    
    float attenuationA = attenuation.getExponent();
    float attenuationB = attenuation.getLinear();
    float attenuationC = attenuation.getConstant() - COLOR_DEPTH * getIntensity() * getColor().max();    
    
    this.range = (float) ( -attenuationB + Math.sqrt( attenuationB * attenuationB - 4 * attenuationA * attenuationC ) ) / ( 2 * attenuationA );
    
    setShader(new Shader("forward-point"));
  }

  public float getRange() {
    return range;
  }

  public void setRange(float range) {
    this.range = range;
  }

public Attenuation getAttenuation() {
  
  return attenuation;
}

  
}
