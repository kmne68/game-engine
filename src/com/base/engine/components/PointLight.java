/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.BaseLight;

/**
 *
 * @author kmne6
 */
public class PointLight extends GameComponent {

  private BaseLight baseLight;
  private Attenuation attenuation;
  private Vector3f position;
  private float range;

  public PointLight(BaseLight baseLight, Attenuation attenuation, Vector3f position, float range) {
    this.baseLight = baseLight;
    this.attenuation = attenuation;
    this.position = position;
    this.range = range;
  }

  @Override
  public void addToRenderingEngine(RenderingEngine renderingEngine) {

    renderingEngine.addPointLight(this);
  }

  public BaseLight getBaseLight() {
    return baseLight;
  }

  public void setBaseLight(BaseLight baseLight) {
    this.baseLight = baseLight;
  }

  public Attenuation getAttenuation() {
    return attenuation;
  }

  public void setAttenuation(Attenuation attenuation) {
    this.attenuation = attenuation;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public float getRange() {
    return range;
  }

  public void setRange(float range) {
    this.range = range;
  }

}
