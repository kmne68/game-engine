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
public class Material {
  
  private static final int DEFAULT_SPECULAR_INTENSITY = 2;
  private static final int DEFAULT_SPECULAR_EXPONENT = 32;
  
  private Texture texture;
  private Vector3f color;
  private float specularIntensity;
  private float specularPower;
  
  public Material(Texture texture) {
    
    this( texture, new Vector3f( 1, 1, 1 ) );
    
  }
  
  public Material(Texture texture, Vector3f color) {
    
    this(texture, color, DEFAULT_SPECULAR_INTENSITY, DEFAULT_SPECULAR_EXPONENT);
  }
  
  public Material(Texture texture, Vector3f color, float specularIntensity, float specularPower) {
    
    this.texture = texture;
    this.color = color;
    this.specularIntensity = specularIntensity;
    this.specularPower = specularPower;
    
  }
  
  
  public Texture getTexture() {
    
    return texture;
    
  }
  
  
  public void setTexture(Texture texture) {
    
    this.texture = texture;
    
  }
  
  
  public Vector3f getColor() {
    
    return color;
    
  }
  
  public void setColor(Vector3f color) {
    
    this.color = color;
    
  }

  public float getSpecularIntensity() {
    return specularIntensity;
  }

  public void setSpecularIntensity(float specularIntensity) {
    this.specularIntensity = specularIntensity;
  }

  public float getSpecularPower() {
    return specularPower;
  }

  public void setSpecularPower(float specularPower) {
    this.specularPower = specularPower;
  }
  
  
}
