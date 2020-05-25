/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author kmne68
 */
public class Game {

  private Mesh mesh;
  private Shader shader;
  private Material material;
  private Transform transform;
  private Camera camera;  
                                                          // these vectors control color                          // these vectors control distance
  PointLight pointLight1 = new PointLight(new BaseLight(new Vector3f(1, 0.5f, 0), 0.8f), new Attenuation(0, 0, 1), new Vector3f(-2, 0, 6f), 10);
  PointLight pointLight2 = new PointLight(new BaseLight(new Vector3f(0, 0.5f, 1), 0.8f), new Attenuation(0, 0, 1), new Vector3f(2, 0, 7f), 10);
    
  SpotLight spotLight1 = new SpotLight(
                          new PointLight(
                            new BaseLight(
                              new Vector3f(0, 1f, 1f), 0.8f), 
                              new Attenuation(0, 0, 0.1f),
                              new Vector3f(-2, 0, 5f), 30),
                              new Vector3f(1, 1, 1), 0.7f);
  
  
  public Game() {

    // material = new Material( null, new Vector3f( 0, 1, 1 ) );
                                                                      // the 1 and 8 are specular intensity and exponent, respectively
    material = new Material( new Texture( "test.png"), new Vector3f( 1, 1, 1 ), 1, 8 );
    System.out.println("*** Game() ***");
    // shader = new Shader();
    // shader = BasicShader.getInstance();
    shader = PhongShader.getInstance();
    camera = new Camera();
    transform = new Transform();

    /* The code commented out in the following lines was used until tutorial #28 */
//    Vertex[] vertices = new Vertex[]{ new Vertex(new Vector3f(-1.0f, -1.0f, 0.5773f),
//      new Vector2f(0.0f, 0.0f)),
//      new Vertex(new Vector3f(0.0f, -1.0f, -1.15475f ),
//      new Vector2f(0.5f, 0.0f)),
//      new Vertex(new Vector3f(1.0f, -1.0f, 0.5773f),
//      new Vector2f(1.0f, 0.0f)),
//      new Vertex(new Vector3f(0.0f, 1.0f, 0.0f),
//      new Vector2f(0.5f, 1.0f))};
//    int[] indices = new int[]{3, 1, 0,
//      2, 1, 3,
//      0, 1, 2,
//      0, 2, 3};
    
    float fieldDepth = 10.0f;
    float fieldWidth = 10.0f;
    
    Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f( -fieldWidth, 0.0f, -fieldDepth ), new Vector2f(0.0f, 0.0f)),
                                       new Vertex( new Vector3f( -fieldWidth, 0.0f, fieldDepth * 3 ), new Vector2f(0.0f, 1.0f)),
                                       new Vertex( new Vector3f( fieldWidth * 3, 0.0f, -fieldDepth ), new Vector2f(1.0f, 0.0f)),
                                       new Vertex( new Vector3f( fieldWidth * 3, 0.0f, fieldDepth * 3 ), new Vector2f(1.0f, 1.0f)) 
                                      };
    
    int indices[] = { 0, 1, 2,
                      2, 1, 3 };

    mesh = new Mesh(vertices, indices, true);
    
    Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
    Transform.setCamera(camera);
    
    PhongShader.setAmbientLight(new Vector3f( 0.1f, 0.1f, 0.1f ) );
    PhongShader.setDirectionalLight(new DirectionalLight( new BaseLight( new Vector3f(1, 1, 1), 0.1f), new Vector3f(1, 1, 1) ) );
     PhongShader.setPointLight( new PointLight[] { pointLight1, pointLight2 } );
    PhongShader.setSpotLight(new SpotLight[] {spotLight1});
  }

  public void input() {

    camera.input();

//        if (Input.getKeyDown(Keyboard.KEY_UP)) {
//            System.out.println("Pressed up");
//        }
//        if (Input.getKeyUp(Keyboard.KEY_UP)) {
//            System.out.println("Released up");
//        }
//
//        if (Input.getMouseDown(1)) {
//            System.out.println("Pressed mouse right at " + Input.getMousePosition().toString());
//        }
//        if (Input.getMouseUp(1)) {
//            System.out.println("Released mouse right");
//        }
  }

  // Temporary variable for testing uniform variables
  float temp = 0.0f;
    // float tempAmount = 0.0f;

  public void update() {

    temp += Time.getDelta();

    float tempSin = (float) Math.sin(temp);

    transform.setTranslation(tempSin, -1, 5);
    // transform.setRotation(0, tempSin * 180, 0);
    // transform.setScale(0.5f * tempSin, 0.5f * tempSin, 0.5f * tempSin);
    
    pointLight1.setPosition(new Vector3f(3, 0, 8.0f * (float)(Math.sin(temp) + 1.0 / 2.0 ) + 10 ));
    pointLight2.setPosition(new Vector3f(7, 0, 8.0f * (float)(Math.cos(temp) + 1.0 / 2.0 ) + 10 ));
    
    // set spotlight's postion to that of the camera
    spotLight1.getPointLight().setPosition(camera.getPosition());
    spotLight1.setDirection(camera.getForward());
    
  }

  public void render() {

    RenderUtil.setClearColor(Transform.getCamera().getPosition().divide(2048f).absolute());
    shader.bind();
    shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
    mesh.draw();
  }
}
