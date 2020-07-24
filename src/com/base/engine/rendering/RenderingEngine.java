/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.components.Camera;
import com.base.engine.components.BaseLight;
import com.base.engine.core.GameObject;
import com.base.engine.core.Transform;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.*;
import com.base.engine.rendering.resourcemanagement.MappedValues;
import java.util.ArrayList;
import java.util.HashMap;
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
public class RenderingEngine extends MappedValues {

  private Camera mainCamera;
  private Vector3f ambientLight;

  private ArrayList<BaseLight> lights;
  private BaseLight activeLight;

//  private Shader forwardAmbient;          // added 2020-07-22
  private HashMap<String, Integer> samplerMap;
//  private HashMap<String, Vector3f> vector3fHashMap;
//  private HashMap<String, Float> floatHashMap;

  public RenderingEngine() {

    super();
    lights = new ArrayList<BaseLight>();
    samplerMap = new HashMap<String, Integer>();

    samplerMap.put("diffuse", 0);
    
    addVector3f("ambient", new Vector3f(0.1f, 0.1f, 0.1f));

  //  forwardAmbient = new Shader("forward-ambient");          // added 2020-07-22
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // All pixels to black

    glFrontFace(GL_CW);
    glCullFace(GL_BACK);        // Don't draw back faces
    glEnable(GL_CULL_FACE);
    glEnable(GL_DEPTH_TEST);    // Draw closer things last

    glEnable(GL_DEPTH_CLAMP);
    glEnable(GL_TEXTURE_2D);

//    mainCamera = new Camera( (float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f );
//    ambientLight = new Vector3f(0.2f, 0.2f, 0.2f);
//    activeDirectionalLight = new DirectionalLight(new BaseLight(new Vector3f(0, 0, 1), 0.4f), new Vector3f(1, 1, 1));
//    directionalLight2 = new DirectionalLight(new BaseLight(new Vector3f(1, 0, 0), 0.4f), new Vector3f(-1, 1, -1));
//    activePointLight = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 0.4f), new Attenuation(0, 0, 1), new Vector3f(3, 0, 3), 100);

//    int lightFieldWidth = 5;
//    int lightFieldDepth = 5;
//    
//    float lightFieldStartX = 0;
//    float lightFieldStartY = 0;
//    float lightFieldStepX = 7;
//    float lightFieldStepY = 7;
//    
//    pointLightList = new PointLight[lightFieldWidth * lightFieldDepth];
//    
//    for(int i = 0; i < lightFieldWidth; i++) {
//      for(int j = 0; j < lightFieldDepth; j++) {
//        pointLightList[i * lightFieldWidth + j] = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 0.4f),
//                                                                  new Attenuation(0, 0, 1),
//                                                                  new Vector3f(lightFieldStartX + lightFieldStepX * i, 0, lightFieldStartY + lightFieldStepY * j), 100);
//      }
//    }
//    
//    activePointLight = pointLightList[0];
//    
//    spotLight = new SpotLight(new PointLight(new BaseLight(new Vector3f(0, 1, 1), 0.8f),
//                              new Attenuation(0, 0, 0.01f), new Vector3f(lightFieldStartX, 0, lightFieldStartY), 100), new Vector3f(1, 0, 0), 0.7f);
  }

//  public Vector3f getAmbientLight() {
//
//    return ambientLight;
//  }

  public void render(GameObject object) {

    // clearScreen();    
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    lights.clear();
    object.addToRenderingEngine(this);

    Shader forwardAmbient = ForwardAmbient.getInstance();

    object.render(forwardAmbient, this);

    glEnable(GL_BLEND);
    glBlendFunc(GL_ONE, GL_ONE);
    glDepthMask(false);
    glDepthFunc(GL_EQUAL);

    for (BaseLight light : lights) {

      activeLight = light;
      object.render(light.getShader(), this);
    }

    glDepthFunc(GL_LESS);
    glDepthMask(true);
    glDisable(GL_BLEND);
  }

//  private void addVector3f(String name, Vector3f vector3f) {
//    vector3fHashMap.put(name, vector3f);
//  }
//
//  private void addFloat(String name, float floatValue) {
//    floatHashMap.put(name, floatValue);
//  }

  
  // Bennie ditched this but I want to keep it
  private static void clearScreen() {
    //TODO: Stencil Buffer
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  }

  public static String getOpenGLVersion() {
    return glGetString(GL_VERSION);
  }

  public void addLight(BaseLight light) {

    lights.add(light);
  }

  public Camera getMainCamera() {

    return mainCamera;
  }

  public BaseLight getActiveLight() {

    return activeLight;
  }

  public int getSamplerSlot(String samplerName) {

    return samplerMap.get(samplerName);
  }

  public void setMainCamera(Camera mainCamera) {

    this.mainCamera = mainCamera;
  }

  public void addCamera(Camera camera) {

    mainCamera = camera;
  }
  
  
  public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType) {
    
    throw new IllegalArgumentException(uniformType + "is not a supported type in RenderingEngine");
  }

}
