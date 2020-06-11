/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

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
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Vertex;

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

    Mesh mesh = new Mesh(vertices, indices, true);
    // the 1 and 8 are specular intensity and exponent, respectively
    Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);

    MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

    GameObject planeObject = new GameObject();
    planeObject.addComponent(meshRenderer);
    planeObject.getTransform().setPosition(0, -1, 5);

    GameObject directionalLightObject = new GameObject();
    DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, 1), 0.4f, new Vector3f(1, 1, 1));
    directionalLightObject.addComponent(directionalLight);

    GameObject pointLightObject = new GameObject();
    pointLightObject.addComponent(new PointLight(new Vector3f(0, 1, 0), 0.4f, new Vector3f(0, 0, 1), new Vector3f(5, 0, 5), 100));
    
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
    SpotLight spotLight = new SpotLight(new Vector3f(1, 0, 1), 0.9f,
            new Vector3f(0, 0, 0.1f),
            new Vector3f(15, 0, 15), 90.0f,
            new Vector3f(1, 0, 0), 0.1f);
    
    GameObject spotLightObject = new GameObject();
    spotLightObject.addComponent(spotLight);

//    GameObject pointLightObject = new GameObject();
//    PointLight pointLight = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 0.4f), new Attenuation(0, 0, 1), new Vector3f(3, 0, 3), 100);
//    pointLightObject.addComponent(pointLight);

    getRootObject().addChild(planeObject);
    getRootObject().addChild(directionalLightObject);
    getRootObject().addChild(pointLightObject);
    getRootObject().addChild(spotLightObject);

  }

}
