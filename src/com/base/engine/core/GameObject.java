/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import java.util.ArrayList;

/**
 *
 * @author kmne6
 */
public class GameObject {
  
  private ArrayList<GameObject> children;
  private ArrayList<GameComponent> components;
  private Transform transform;
  
  
  public GameObject() {
    
    children = new ArrayList<GameObject>();
    components = new ArrayList<GameComponent>();
    transform = new Transform();
    
  }
  
  
  public void addChild(GameObject child) {
    
    children.add(child);
    
  }
  
  
  public void addComponent(GameComponent component) {
    
    components.add(component);
    
  }
  
  
  public void input() {
    
    for(GameComponent component : components)
      component.input(transform);
    
    for(GameObject child : children)
      child.input();
  }
  
  
  public void update() {
        
    for(GameComponent component : components)
      component.update(transform);
    
    for(GameObject child : children)
      child.update();
    
  }
  
  
  public void render() {
        
    for(GameComponent component : components)
      component.render(transform);
    
    for(GameObject child : children)
      child.render();
    
  }
  
  
  public Transform getTransform() {
    
    return transform;
    
  }

  
}
