/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.engine.components.GameComponent;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

/**
 *
 * @author kmne6
 */
public class LookAtComponent extends GameComponent {
  
  RenderingEngine renderingEngine;
  
  @Override
  public void update(float delta) {
    
    if(renderingEngine != null) {
      
      Quaternion newRotation = getTransform().getLookAtDirection(renderingEngine.getMainCamera().getTransform().getTransformPosition(),
              new Vector3f(0, 1, 0));
      // getTransform().getRotation().getUp());
      
      getTransform().setRotation(getTransform().getRotation().nlerp(newRotation, delta * 5.0f, true));
    //  getTransform().setRotation(getTransform().getRotation().slerp(newRotation, delta * 5.0f, true));
    }
  }
    
    @Override
    public void render(Shader shader, RenderingEngine renderingEngine) {
      
      this.renderingEngine = renderingEngine;    
    }
  
}
