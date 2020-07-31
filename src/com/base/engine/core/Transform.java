/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import com.base.engine.components.Camera;

/**
 *
 * @author kmne6
 */
public class Transform {

  private Transform parent;
  private Matrix4f parentMatrix;

  private Vector3f position;
  private Quaternion rotation;
  private Vector3f scale;

  // variables to track prior attribute values
  private Vector3f oldPosition;
  private Quaternion oldRotation;
  private Vector3f oldScale;

  public Transform() {

    position = new Vector3f(0, 0, 0);
    rotation = new Quaternion(0, 0, 0, 1);
    scale = new Vector3f(1, 1, 1);

    parentMatrix = new Matrix4f().initializeIdentity();
  }
  
  
  public void update() {
    
    if (oldPosition != null) {
      oldPosition.set(position);
      oldRotation.set(rotation);
      oldScale.set(scale);
    }
    else {
      oldPosition = new Vector3f(0, 0, 0).set(position).add(1.0f);
      oldRotation = new Quaternion(0, 0, 0, 0).set(rotation).multiplyFloat(0.5f);
      oldScale = new Vector3f(0, 0, 0).set(scale).add(1.0f);
    }    
  }
  
  
  public void lookAt(Vector3f point, Vector3f up) {
    
    rotation = getLookAtDirection(point, up);
  
  }
  
  
  public Quaternion getLookAtDirection(Vector3f point, Vector3f up) {
    
    return new Quaternion(new Matrix4f().initializeRotation(point.subtract(position).normalize(), up));
  }
  
  
  public void rotate(Vector3f axis, float angle) {
    
    rotation = new Quaternion(axis, angle).multiplyQuaternion(rotation).normalize();    
  }
  

  public boolean hasChanged() {

    if (parent != null && parent.hasChanged()) {
      return true;
    }

    if (!position.equals(oldPosition)) {
      return true;
    }
    if (!rotation.equals(oldRotation)) {
      return true;
    }
    if (!scale.equals(oldScale)) {
      return true;
    }

    return false;

  }

  public Matrix4f getTransformation() {
    
    Matrix4f translationMatrix = new Matrix4f().initializeTranslation(position.getX(),
                                                                      position.getY(),
                                                                      position.getZ());
    Matrix4f rotationMatrix = rotation.toRotationMatrix();
    Matrix4f scaleMatrix = new Matrix4f().initializeScale(scale.getX(), scale.getY(), scale.getZ());

    // Apply scale transformation first
    return getParentMatrix().multiplyMatrix(translationMatrix.multiplyMatrix(rotationMatrix.multiplyMatrix(scaleMatrix)));

  }

  public void setParent(Transform parent) {

    this.parent = parent;
  }
  
  
  public Vector3f getTransformPosition() {
    
    return getParentMatrix().transform(position);
  }
  
  
  public Quaternion getTransformRotation() {
    
    Quaternion parentRotation = new Quaternion(0, 0, 0, 1);   // corresponds to no rotation
    
    if(parent != null) {
      parentRotation = parent.getTransformRotation();
    }
    
    return parentRotation.multiplyQuaternion(rotation);
  }
  

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public void setPosition(float x, float y, float z) {
    this.position = new Vector3f(x, y, z);
  }

  public Quaternion getRotation() {
    return rotation;
  }

  public void setRotation(Quaternion rotation) {
    this.rotation = rotation;
  }

  public Vector3f getScale() {
    return scale;
  }

  public void setScale(Vector3f scale) {
    this.scale = scale;
  }

  /**
   * PRIVATE METHODS
   */
  private Matrix4f getParentMatrix() {

    if (parent != null && parent.hasChanged())
      parentMatrix = parent.getTransformation();
    
    return parentMatrix;
  }

}
