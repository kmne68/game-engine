/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

/**
 *
 * @author kmne6
 */
public abstract class Game {
  
  
  private GameObject root;
  
  public void init() {}
  
  
  public void input() {
  
    getRootObject().input();
  }
  
  public void update() {
  
    getRootObject().update();
  }  
  
  public GameObject getRootObject() {
    
    if(root == null)
      root = new GameObject();
    
    return root;
    
  }
  
}
