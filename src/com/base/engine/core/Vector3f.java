/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

/**
 *
 * @author kmne68
 */
public class Vector3f {

  private float x;
  private float y;
  private float z;

  public Vector3f(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public float length() {
    return (float) Math.sqrt(x * x + y * y + z * z);
  }
  
  public float max() {
    
    return Math.max(x, Math.max(y, z));
  }

  /**
   * Convert axis of rotation into a quaternion
   *
   * @param angle
   * @param axis
   * @return
   */
  public Vector3f rotate(Vector3f axis, float angle) {
    
    float sinAngle = (float) Math.sin(-angle);
    float cosAngle = (float) Math.cos(-angle);
    
    return this.crossProduct(axis.multiply(sinAngle)).add(                      // rotation on local X
            (this.multiply(cosAngle)).add(                                      // rotation on local Z
                    axis.multiply(this.dot(axis.multiply(1 - cosAngle)))));     // rotation on local Y
    
  }
  
  
  public Vector3f rotate(Quaternion rotation) {

    Quaternion conjugate = rotation.conjugate();

    Quaternion w = rotation.multiplyVector(this).multiplyQuaternion(conjugate);

    return new Vector3f( w.getX(), w.getY(), w.getZ() );
  }
  
  
  public Vector3f lerp(Vector3f destination, float lerpFactor) {
    
    return destination.subtract(this).multiply(lerpFactor).add(this);
  }

  // The dot product of this vector and another
  public float dot(Vector3f r) {
    return x * r.getX() + y * r.getY() + z * r.getZ();
  }

  public Vector3f normalize() {
    
    float length = length();

    return new Vector3f( x / length, y / length, z / length );
  }

  public Vector3f crossProduct(Vector3f r) {
    float x_ = y * r.getZ() - z * r.getY();
    float y_ = z * r.getX() - x * r.getZ();
    float z_ = x * r.getY() - y * r.getX();

    return new Vector3f(x_, y_, z_);
  }

  public Vector3f add(Vector3f r) {
    return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
  }

  public Vector3f add(float r) {
    return new Vector3f(x + r, y + r, z + r);
  }

  public Vector3f subtract(Vector3f r) {
    return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
  }

  public Vector3f subtract(float r) {
    return new Vector3f(x - r, y - r, z - r);
  }

  public Vector3f multiply(Vector3f r) {
    return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
  }

  public Vector3f multiply(float r) {
    return new Vector3f(x * r, y * r, z * r);
  }

  public Vector3f divide(Vector3f r) {
    return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
  }

  public Vector3f divide(float r) {
    return new Vector3f(x / r, y / r, z / r);
  }

  public Vector3f absolute() {
    return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }
  
  public Vector2f getXY() {
    
    return new Vector2f(x, y);
  }

  public Vector2f getYZ() {
    
    return new Vector2f(y, z);
  }
  
  public Vector2f getZX() {
    
    return new Vector2f(z, x);
  }
  
  public Vector2f getYX() {
    
    return new Vector2f(y, x);
  }
  
  public Vector2f getZY() {
    
    return new Vector2f(z, y);
  }
  
  public Vector2f getXZ() {
    
    return new Vector2f(x, z);
  }
  
  public Vector3f setVector3f(float x, float y, float z) {
    
    this.x = x;
    this.y = y;
    this.z = z;
    
    return this;
    
  }
  
  
  public Vector3f set(Vector3f r) { 
    
    setVector3f(r.getX(), r.getY(), r.getZ());
    
    return this;
  }
  
  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getZ() {
    return z;
  }

  public void setZ(float z) {
    this.z = z;
  }
  
  public boolean equal(Vector3f r) {
    
    return x == r.getX() && y == r.getY() && z == r.getZ();
  }

}
