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

  public static final Vector3f yAxis = new Vector3f(0, 1, 0);


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
  

  boolean mouseLocked = false;

  Vector2f centerPosition = new Vector2f( Window.getWidth() / 2, Window.getHeight() / 2 );

  
  public void move(Vector3f direction, float amount) {
    
    getTransform().setPosition(getTransform().getPosition().add(direction.multiply(amount)));
  }

  
  @Override
  public void input(float delta) {
    float sensitivity = 0.5f;
    float moveAmount = (float) (10 * delta);
    //     float rotationAmount = (float)(100 * Time.getDelta());

    if (Input.getKey(Input.KEY_ESCAPE)) {
      Input.setCursor(true);
      mouseLocked = false;
    }

    if (Input.getMouseDown(0)) {
      Input.setMousePosition(centerPosition);
      Input.setCursor(false);
      mouseLocked = true;
    }

    if (Input.getKey(Input.KEY_W)) {
      move(getTransform().getRotation().getForward(), moveAmount);
    }
    if (Input.getKey(Input.KEY_S)) {
      move(getTransform().getRotation().getForward(), -moveAmount);
    }
    if (Input.getKey(Input.KEY_A)) {
      move(getTransform().getRotation().getLeft(), moveAmount);
    }
    if (Input.getKey(Input.KEY_D)) {
      move(getTransform().getRotation().getRight(), moveAmount);
    }

    if (mouseLocked) {
      Vector2f deltaPosition = Input.getMousePosition().subtract(centerPosition);

      boolean rotateY = deltaPosition.getX() != 0;
      boolean rotateX = deltaPosition.getY() != 0;

      if (rotateY)
        getTransform().rotate(yAxis, (float) Math.toRadians( deltaPosition.getX() * sensitivity ) );
      
      if (rotateX) {
        getTransform().rotate(getTransform().getRotation().getRight(),
                        ( (float) Math.toRadians(-deltaPosition.getY() * sensitivity ) ) );
      }

      if (rotateY || rotateX) {
        Input.setMousePosition(new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2));
      }
    }

  }


}
