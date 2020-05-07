/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

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

  /**
   * Convert axis of rotation into a quaternion
   *
   * @param angle
   * @param axis
   * @return
   */
  public Vector3f rotate(float angle, Vector3f axis) {
    float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
    float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

    float rX = axis.getX() * sinHalfAngle;
    float rY = axis.getY() * sinHalfAngle;
    float rZ = axis.getZ() * sinHalfAngle;
    float rW = 1 * cosHalfAngle;

    Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
    Quaternion conjugate = rotation.conjugate();

    Quaternion w = rotation.multiplyVector(this).multiplyQuaternion(conjugate);

    x = w.getX();
    y = w.getY();
    z = w.getZ();

    return this;
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

}
