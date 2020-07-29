/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.engine.components.Camera;
import com.base.engine.components.MeshRenderer;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector3f;
import com.base.engine.core.Vector2f;
import com.base.engine.components.DirectionalLight;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Mesh;
import com.base.engine.components.PointLight;
import com.base.engine.components.SpotLight;
import com.base.engine.core.Quaternion;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Vertex;
import com.base.engine.rendering.Window;

/**
 *
 * @author kmne68
 */
public class TestGame extends Game {

  public void init() {

    float fieldDepth = 10.0f;
    float fieldWidth = 10.0f;

    Vertex[] vertices = new Vertex[]{new Vertex(new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
      new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
      new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
      new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))
    };

    int indices[] = {0, 1, 2, 
      2, 1, 3};

    Vertex[] vertices2 = new Vertex[]{new Vertex(new Vector3f(-fieldWidth / 10, 0.0f, -fieldDepth / 10), new Vector2f(0.0f, 0.0f)),
      new Vertex(new Vector3f(-fieldWidth / 10, 0.0f, fieldDepth / 10 * 3), new Vector2f(0.0f, 1.0f)),
      new Vertex(new Vector3f(fieldWidth / 10 * 3, 0.0f, -fieldDepth / 10), new Vector2f(1.0f, 0.0f)),
      new Vertex(new Vector3f(fieldWidth / 10 * 3, 0.0f, fieldDepth / 10 * 3), new Vector2f(1.0f, 1.0f))
    };
    
    int indices2[] = { 0, 1, 2,
      2, 1, 3 };
    
    Mesh mesh2 = new Mesh(vertices2, indices2, true);
    
    Mesh mesh = new Mesh(vertices, indices, true);
    // the 1 and 8 are specular intensity and exponent, respectively
    Material material = new Material(); // (new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
    // material.addTexture("diffuse", new Texture("test.png"));
    material.addTexture("diffuse", new Texture("bricks.jpg"));
    material.addFloat("specularIntensity", 1);
    material.addFloat("specularPower", 8);
    
    Material material2 = new Material(); // (new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
    material2.addTexture("diffuse", new Texture("grid_texture.png"));
    material2.addFloat("specularIntensity", 1);
    material2.addFloat("specularPower", 8);
    
    Mesh monkeyMesh = new Mesh("engine_monkey.obj");    // Benny calls this "tempMesh"

    MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

    GameObject planeObject = new GameObject();
    planeObject.addComponent(meshRenderer);
    planeObject.getTransform().getPosition().setVector3f(0, -1, 5);

    GameObject directionalLightObject = new GameObject();
    DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, 1), 0.4f);
    directionalLightObject.addComponent(directionalLight);

    GameObject pointLightObject = new GameObject();
    pointLightObject.addComponent(new PointLight(new Vector3f(0, 1, 0), 0.4f, new Attenuation(0, 0, 1)));
    
    /**
     * 
     * @params
     * Vector3f   color
     * float      intensity
     * Vector3f   attenuation (comprises constant, linear, and exponent properties)
     * Vector3f   position
     * float      range
     * Vector3f   direction
     * float      cutoff
     * 
     */
    SpotLight spotLight = new SpotLight(new Vector3f(0, 1, 1), 0.4f,
            new Attenuation(0, 0, 0.1f), 0.7f);
    
    GameObject spotLightObject = new GameObject();
    spotLightObject.addComponent(spotLight);
    
    spotLightObject.getTransform().getPosition().setVector3f(5, 0, 5);
    spotLightObject.getTransform().setRotation(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90.0f)));

//    GameObject pointLightObject = new GameObject();
//    PointLight pointLight = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 0.4f), new Attenuation(0, 0, 1), new Vector3f(3, 0, 3), 100);
//    pointLightObject.addComponent(pointLight);

    addObject(planeObject);
    addObject(directionalLightObject);
    addObject(pointLightObject);
    addObject(spotLightObject);
    
    // getRootObject().addChild(new GameObject().addComponent(new Camera( (float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f)));

    // Test Code from episode #46
    GameObject testMesh1 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
    GameObject testMesh2 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
    GameObject testMesh3 = new GameObject().addComponent(new MeshRenderer(monkeyMesh, material));
    
    testMesh1.getTransform().getPosition().setVector3f(0, 2, 0);
    testMesh1.getTransform().setRotation( new Quaternion( new Vector3f( 0, 1, 0 ), 0.4f ) );
    
    
    testMesh2.getTransform().getPosition().setVector3f(0, 0, 5);
    
    testMesh1.addChild(testMesh2);
    testMesh2.addChild(new GameObject().addComponent(new Camera( (float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f)));
    // getRootObject().addChild(new GameObject().addComponent(new Camera( (float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f)));
    
    addObject(testMesh1);
    addObject(testMesh3);
    
    testMesh3.getTransform().getPosition().setVector3f(5, 5, 5);
    testMesh3.getTransform().setRotation(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-70.0f)));
    
    addObject(new GameObject().addComponent(new MeshRenderer(new Mesh("engine_monkey.obj"), material2)));
    
    directionalLight.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(-45)));
  }

}
