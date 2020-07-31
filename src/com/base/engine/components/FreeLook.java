/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.Input;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Window;

/**
 *
 * @author kmne6
 */
public class FreeLook extends GameComponent{
  
  
  public static final Vector3f yAxis = new Vector3f(0, 1, 0);  
  boolean mouseLocked = false;
  Vector2f centerPosition = new Vector2f( Window.getWidth() / 2, Window.getHeight() / 2 );
  
 
  @Override
  public void input(float delta) {
    
    float sensitivity = 0.5f;

    if (Input.getKey(Input.KEY_ESCAPE)) {
      Input.setCursor(true);
      mouseLocked = false;
    }

    if (Input.getMouseDown(0)) {
      Input.setMousePosition(centerPosition);
      Input.setCursor(false);
      mouseLocked = true;
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
