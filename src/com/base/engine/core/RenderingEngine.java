/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import com.base.engine.rendering.BasicShader;
import com.base.engine.rendering.Camera;
import com.base.engine.rendering.Shader;
import com.base.engine.rendering.Window;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

/**
 *
 * @author kmne6
 */
public class RenderingEngine {
  
  private Camera mainCamera;
  
  public RenderingEngine() {
    
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // All pixels to black

    glFrontFace(GL_CW);
    glCullFace(GL_BACK);        // Don't draw back faces
    glEnable(GL_CULL_FACE);
    glEnable(GL_DEPTH_TEST);    // Draw closer things last

    glEnable(GL_DEPTH_CLAMP);
    glEnable(GL_TEXTURE_2D);
    
    mainCamera = new Camera( (float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f );
  }
  
  public void input() {
    
    mainCamera.input();
  }
  
  public void render(GameObject object) {
    
    clearScreen();
    
    Shader shader = BasicShader.getInstance();
    shader.setRenderingEngine(this);
    object.render(BasicShader.getInstance());
  }
  
    private static void clearScreen() {
    //TODO: Stencil Buffer
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  }

  public static String getOpenGLVersion() {
    return glGetString(GL_VERSION);
  }
  
  
  private static void setClearColor(Vector3f color) {
    
    glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    
  }

  private static void setTextures(boolean enabled) {
    if (enabled) {
      glEnable(GL_TEXTURE_2D);
    } else {
      glDisable(GL_TEXTURE_2D);
    }
  }
  
  private static void unbindTextures() {
    
    glBindTexture(GL_TEXTURE_2D, 0);
    
  }

  public Camera getMainCamera() {
    
    return mainCamera;
  }

  public void setMainCamera(Camera mainCamera) {
    
    this.mainCamera = mainCamera;
  }
  
}
