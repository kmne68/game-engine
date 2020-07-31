/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import static com.base.engine.components.FreeLook.yAxis;
import com.base.engine.core.Input;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Window;

/**
 *
 * @author kmne6
 */
public class FreeMove extends GameComponent {  
  
  @Override
  public void input(float delta) {
    
    float moveAmount = (float) (10 * delta);

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

  }
  
    public void move(Vector3f direction, float amount) {
    
    getTransform().setPosition(getTransform().getPosition().add(direction.multiply(amount)));
  }
}
