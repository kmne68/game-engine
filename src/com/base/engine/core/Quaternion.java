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
public class Quaternion {

  private float x;
  private float y;
  private float z;
  private float w;

  
  public Quaternion(float x, float y, float z, float w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  public Quaternion(Vector3f axis, float angle) {

    float sinHalfAngle = (float) Math.sin((angle / 2));
    float cosHalfAngle = (float) Math.cos((angle / 2));

    this.x = axis.getX() * sinHalfAngle;
    this.y = axis.getY() * sinHalfAngle;
    this.z = axis.getZ() * sinHalfAngle;
    this.w = 1 * cosHalfAngle;

  }
  

  public float length() {
    return (float) Math.sqrt(x * x + y * y + z * z + w * w);
  }

  public Quaternion normalize() {
    float length = length();

    return new Quaternion(x / length, y / length, z / length, w / length);
  }

  public Quaternion conjugate() {
    return new Quaternion(-x, -y, -z, w);
  }
  
  
  public boolean equals(Quaternion r) {
    
    return x == r.getX() && y == r.getY() && z == r.getZ() && w == r.getW();
  }
  
  
  public Quaternion add(Quaternion r) {
    
    return new Quaternion(x + r.getX(), y + r.getY(), z + r.getZ(), w + r.getW());
    
  }
  
  
  public float dot(Quaternion r) {
    
    return x * r.getX() + y * r.getY() + z * r.getZ() + w * r.getW();
  }
  
  
  public Quaternion multiplyFloat(Float r) {
    
    return new Quaternion(x * r, y * r, z * r, w * r);
  }
  

  public Quaternion multiplyQuaternion(Quaternion r) {
    float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
    float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
    float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
    float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();

    return new Quaternion(x_, y_, z_, w_);
  }

  public Quaternion multiplyVector(Vector3f r) {
    float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
    float x_ = w * r.getX() + y * r.getZ() - z * r.getY();
    float y_ = w * r.getY() + z * r.getX() - x * r.getZ();
    float z_ = w * r.getZ() + x * r.getY() - y * r.getX();

    return new Quaternion(x_, y_, z_, w_);
  }
  
  
  /**
   * Normalized Linear Interpolation (nlerp)
   * 
   * @param Quaternion
   *        float
   *        boolean
   * 
   * @return Quaternion
   */
  public Quaternion nlerp(Quaternion destination, float lerpFactor, boolean shortest) {
    
    Quaternion correctedDestination = destination;
    
    if(shortest && this.dot(destination) < 0) {
      correctedDestination = new Quaternion(-destination.getX(), -destination.getY(), -destination.getZ(), -destination.getW());
    }
    
    return correctedDestination.subtract(this).multiplyFloat(lerpFactor).add(this).normalize();
    
  }
  
  
  /**
   * Spherical Linear Interpolation (slerp)
   * @param Quaternion
   *        float
   *        boolean
   * @return Quaternion
   */
  public Quaternion slerp(Quaternion destination, float lerpFactor, boolean shortest) {
    
    final float EPSILON = 1e3f;
    
    float cos = this.dot(destination);
    Quaternion correctedDestination = destination;
    
    if(shortest && cos < 0) {
      
      cos = -cos;
      correctedDestination = new Quaternion(-destination.getX(), -destination.getY(), -destination.getZ(), -destination.getW());
      
    }
    
    if(Math.abs(cos) >= 1 - EPSILON)
      return nlerp(correctedDestination, lerpFactor, false);
    
    float sin = (float)Math.sqrt(1.0f - cos * cos);
    float angle = (float)Math.atan2(sin, cos);
    float inverseSin = 1.0f / sin;
    
    float sourceFactor = (float)Math.sin((1.0f - lerpFactor) * angle ) * inverseSin;
    float destinationFactor = (float)Math.sin((lerpFactor) * angle) * inverseSin;
    
    return this.multiplyFloat(sourceFactor).add(correctedDestination.multiplyFloat(destinationFactor));
        
  }
  
  
  /**
   * From Ken Shoemake's "Quaternion Calculus and Fast Animation" article
   */
  public Quaternion(Matrix4f rotation) {
    
    float trace = rotation.get(0, 0) + rotation.get(1, 1) + rotation.get(2, 2);
  
    if(trace > 0)
    {
      float s = 0.5f / (float)Math.sqrt(trace+ 1.0f);
      w = 0.25f / s;
      x = (rotation.get(1, 2) - rotation.get(2, 1)) * s;
      y = (rotation.get(2, 0) - rotation.get(0, 2)) * s;
      z = (rotation.get(0, 1) - rotation.get(1, 0)) * s;
    }
    else
    {
      if(rotation.get(0, 0) > rotation.get(1, 1) && rotation.get(0, 0) > rotation.get(2, 2))
      {
        float s = 2.0f * (float) Math.sqrt(1.0f + rotation.get(0, 0) - rotation.get(1, 1) - rotation.get(2, 2));
        w = (rotation.get(1, 2) - rotation.get(2, 1)) / s;
        x = 0.25f * s;
        y = (rotation.get(1, 0) - rotation.get(0, 1)) / s;
        z = (rotation.get(2, 0) - rotation.get(0, 2)) / s;
      }
      else if(rotation.get(1, 1) > rotation.get(2, 2))
      {
        float s = 2.0f * (float) Math.sqrt(1.0f + rotation.get(1, 1) - rotation.get(0, 0) - rotation.get(2, 2));
        w = (rotation.get(2, 0) - rotation.get(0, 2)) / s;
        x = (rotation.get(1, 0) + rotation.get(0, 1)) / s;
        y = 0.25f * s;
        z = (rotation.get(2, 1) - rotation.get(1, 2)) / s;        
      }
      else
      {
        float s = 2.0f * (float) Math.sqrt(1.0f + rotation.get(2, 2) - rotation.get(0, 0) - rotation.get(1, 1));
        w = (rotation.get(0, 1) - rotation.get(1, 0)) / s;
        x = (rotation.get(2, 0) + rotation.get(0, 2)) / s;
        y = (rotation.get(1, 2) + rotation.get(2, 1)) / s;
        z = 0.25f * s;
      }
    }
    float length = (float)Math.sqrt(x * x + y * y + z * z + w * w);
    x /= length;
    y /= length;
    z /= length;
    w /= length;
    
  }
  
  
  public Quaternion subtract(Quaternion r) {
    
    return new Quaternion(x - r.getX(), y - r.getY(), z - r.getZ(), w - r.getW());
    
  }
  

  public Matrix4f toRotationMatrix() {

    Vector3f forward = new Vector3f(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0f - 2.0f * (x * x + y * y ) );
    Vector3f up = new Vector3f(2.0f * (x * y + w * z), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x ) );
    Vector3f right = new Vector3f(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));
    
    return new Matrix4f().initializeRotation(forward, up, right);
  }

  public Vector3f getForward() {

    return new Vector3f(0, 0, 1).rotate(this);
  }

  public Vector3f getBack() {

    return new Vector3f(0, 0, -1).rotate(this);
  }

  public Vector3f getUp() {

    return new Vector3f(0, 1, 0).rotate(this);
  }

  public Vector3f getDown() {

    return new Vector3f(0, -1, 0).rotate(this);
  }

  public Vector3f getRight() {

    return new Vector3f(1, 0, 0).rotate(this);
  }

  public Vector3f getLeft() {

    return new Vector3f(-1, 0, 0).rotate(this);
  }
  
  
  public Quaternion setVector3f(float x, float y, float z, float w) {
    
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
    
    return this;
    
  }
  
  
  public Quaternion set(Quaternion r) { 
    
    setVector3f(r.getX(), r.getY(), r.getZ(), r.getW());
    
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

  public float getW() {
    return w;
  }

  public void setW(float w) {
    this.w = w;
  }
}
