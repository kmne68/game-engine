/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.components;

import com.base.engine.core.GameObject;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.core.Transform;
import com.base.engine.rendering.Shader;

/**
 *
 * @author kmne6
 */
public abstract class GameComponent {
  
  private GameObject parent;

  public void input(float delta){} 
  public void update(float delta) {} 
  public void render(Shader shader, RenderingEngine renderingEngine) {}
  
  
  public void setParent(GameObject parent) {
    
    this.parent = parent;
  }
  
  public Transform getTransform() {
    
    return parent.getTransform();
  }
  
  public void addToRenderingEngine(RenderingEngine renderingEngine) {  }
  
}
