/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import com.base.engine.rendering.*;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_EQUAL;
import static org.lwjgl.opengl.GL11.GL_LESS;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
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
  private Vector3f ambientLight;
  private DirectionalLight directionalLight;
  private DirectionalLight directionalLight2;
  private PointLight pointLight;
  private SpotLight spotLight;
  
  private PointLight[] pointLightList;
  
  public RenderingEngine() {
    
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // All pixels to black

    glFrontFace(GL_CW);
    glCullFace(GL_BACK);        // Don't draw back faces
    glEnable(GL_CULL_FACE);
    glEnable(GL_DEPTH_TEST);    // Draw closer things last

    glEnable(GL_DEPTH_CLAMP);
    glEnable(GL_TEXTURE_2D);
    
    mainCamera = new Camera( (float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f );
    ambientLight = new Vector3f(0.2f, 0.2f, 0.2f);
    directionalLight = new DirectionalLight(new BaseLight(new Vector3f(0, 0, 1), 0.4f), new Vector3f(1, 1, 1));
    directionalLight2 = new DirectionalLight(new BaseLight(new Vector3f(1, 0, 0), 0.4f), new Vector3f(-1, 1, -1));
    pointLight = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 0.4f), new Attenuation(0, 0, 1), new Vector3f(3, 0, 3), 100);
    
    int lightFieldWidth = 5;
    int lightFieldDepth = 5;
    
    float lightFieldStartX = 0;
    float lightFieldStartY = 0;
    float lightFieldStepX = 7;
    float lightFieldStepY = 7;
    
    pointLightList = new PointLight[lightFieldWidth * lightFieldDepth];
    
    for(int i = 0; i < lightFieldWidth; i++) {
      for(int j = 0; j < lightFieldDepth; j++) {
        pointLightList[i * lightFieldWidth + j] = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 0.4f),
                                                                  new Attenuation(0, 0, 1),
                                                                  new Vector3f(lightFieldStartX + lightFieldStepX * i, 0, lightFieldStartY + lightFieldStepY * j), 100);
      }
    }
    
    pointLight = pointLightList[0];
    
    spotLight = new SpotLight(new PointLight(new BaseLight(new Vector3f(0, 1, 1), 0.8f),
                              new Attenuation(0, 0, 0.01f), new Vector3f(lightFieldStartX, 0, lightFieldStartY), 100), new Vector3f(1, 0, 0), 0.7f);
    
  }
  
  
  public Vector3f getAmbientLight() {
    
    return ambientLight;
  }
  
  public DirectionalLight getDirectionalLight() {
    
    return directionalLight;
  }
  
  public PointLight getPointLight() {
    
    return pointLight;
  }
  
  public SpotLight getSpotLight() {
    
    return spotLight;
  }
  
  public void input(float delta) {
    
    mainCamera.input(delta);
  }
  
  public void render(GameObject object) {
    
    clearScreen();
    
    Shader forwardAmbient = ForwardAmbient.getInstance();
    Shader forwardPoint = ForwardPoint.getInstance();
    Shader forwardDirectional = ForwardDirectional.getInstance();
    Shader forwardSpot = ForwardSpot.getInstance();
    
    forwardAmbient.setRenderingEngine(this);
    forwardDirectional.setRenderingEngine(this);
    forwardPoint.setRenderingEngine(this);
    forwardSpot.setRenderingEngine(this);
    
    object.render(forwardAmbient);
    
    glEnable(GL_BLEND);    
    glBlendFunc(GL_ONE, GL_ONE);
    glDepthMask(false);    
    glDepthFunc(GL_EQUAL);
    
    object.render(forwardDirectional);
    
    // experiment
    DirectionalLight temp = directionalLight;
    directionalLight = directionalLight2;
    directionalLight2 = temp;
    
    object.render(forwardDirectional);
    
    temp = directionalLight;
    directionalLight = directionalLight2;
    directionalLight2 = temp;
    
    for(int i = 0; i < pointLightList.length; i++) {
      pointLight = pointLightList[i];
      object.render(forwardPoint);
    }
    
    object.render(forwardSpot);
    
    glDepthFunc(GL_LESS);    
    glDepthMask(true);
    glDisable(GL_BLEND);
//    Shader shader = BasicShader.getInstance();
//    shader.setRenderingEngine(this);
//    object.render(BasicShader.getInstance());
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
