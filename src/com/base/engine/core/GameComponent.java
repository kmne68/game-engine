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
public interface GameComponent {

  public void input(Transform transform);  
  public void update(Transform transform); 
  public void render(Transform transform);      
         
  
}
