/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import com.base.engine.rendering.RenderingEngine;
import com.base.engine.components.GameComponent;
import com.base.engine.rendering.Shader;
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
    component.setParent(this);
    
  }
  
  
  public void input(float delta) {
    
    for(GameComponent component : components)
      component.input(delta);
    
    for(GameObject child : children)
      child.input(delta);
  }
  
  
  public void update(float delta) {
        
    for(GameComponent component : components)
      component.update(delta);
    
    for(GameObject child : children)
      child.update(delta);
    
  }
  
  
  public void render(Shader shader) {
        
    for(GameComponent component : components)
      component.render(shader);
    
    for(GameObject child : children)
      child.render(shader);
    
  }
  
  
  public void addToRenderingEngine(RenderingEngine renderingEngine) {
    
    for(GameComponent component : components)
      component.addToRenderingEngine(renderingEngine);
    
    for(GameObject child : children)
      child.addToRenderingEngine(renderingEngine);
  }
  
  
  public Transform getTransform() {
    
    return transform;
    
  }

  
}
