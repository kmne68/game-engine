/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.engine.core.GameComponent;
import com.base.engine.core.Transform;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Shader;

/**
 *
 * @author kmne6
 */
public class MeshRenderer implements GameComponent {
  
  private Mesh mesh;
  private Material material;
  
  public MeshRenderer(Mesh mesh, Material material) {
    
    this.mesh = mesh;
    this.material = material;
  }
  
  @Override
  public void render(Transform transform, Shader shader) {
        
    shader.bind();
    shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
    mesh.draw();
  }

  @Override
  public void input(Transform transform) {
  }

  @Override
  public void update(Transform transform) {
  }
  
}
