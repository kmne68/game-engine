/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import com.base.engine.rendering.RenderingEngine;

/**
 *
 * @author kmne6
 */
public abstract class Game {
  
  
  private GameObject root;
  
  public void init() {}
  
  
  public void input(float delta) {
  
    getRootObject().input(delta);
  }
  
  public void update(float delta) {
  
    getRootObject().update(delta);
  }
  
  
  public void render(RenderingEngine renderingEngine) {
    
    renderingEngine.render(getRootObject());
  }
  
  
  public void addObject(GameObject gameObject) {
    
    getRootObject().addChild(gameObject);
  }
  
  // PRIVATE METHODS
  
  private GameObject getRootObject() {
    
    if(root == null)
      root = new GameObject();
    
    return root;
    
  }
  
}
