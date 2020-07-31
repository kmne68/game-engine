/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.*;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Window;

/**
 *
 * @author kmne6
 */
public class Camera extends GameComponent {

  private Matrix4f projection;

  public Camera(float fieldOfView, float aspect, float zNear, float zFar) {

    this.projection = new Matrix4f().initializePerspective(fieldOfView, aspect, zNear, zFar);

  }

  
  public Matrix4f getViewProjection() {

    Matrix4f cameraRotation = getTransform().getTransformRotation().conjugate().toRotationMatrix();
    Vector3f cameraPosition = getTransform().getTransformPosition().multiply(-1);
    Matrix4f cameraTranslation = new Matrix4f().initializeTranslation(cameraPosition.getX(),
                                                                      cameraPosition.getY(),
                                                                      cameraPosition.getZ());

    return projection.multiplyMatrix(cameraRotation.multiplyMatrix(cameraTranslation));
  }
  
  
  @Override
  public void addToEngine(CoreEngine engine) {
    
    engine.getRenderingEngine().addCamera(this);
  }
  
}
