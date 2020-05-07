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

  private Material material;
  private Mesh mesh;
  private Shader shader;
  private Transform transform;
  private Camera camera;

  public Game() {

    mesh = ResourceLoader.loadMesh("cube.obj");
    // material = new Material( null, new Vector3f( 0, 1, 1 ) );
    material = new Material( ResourceLoader.loadTexture( "test.png"), new Vector3f( 0, 1, 1 ) );
    shader = BasicShader.getInstance();
    camera = new Camera();

//    Vertex[] vertices = new Vertex[]{new Vertex(new Vector3f(-1, -1, 0),
//      new Vector2f(0, 0)),
//      new Vertex(new Vector3f(0, 1, 0),
//      new Vector2f(0.5f, 0)),
//      new Vertex(new Vector3f(1, -1, 0),
//      new Vector2f(1.0f, 0)),
//      new Vertex(new Vector3f(0, -1, 1),
//      new Vector2f(0.0f, 0.5f))};
//    int[] indices = new int[]{3, 1, 0,
//      2, 1, 3,
//      0, 1, 2,
//      0, 2, 3};

//    mesh.addVertices(vertices, indices);
    
    Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
    Transform.setCamera(camera);
    transform = new Transform();

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

    transform.setTranslation(tempSin, 0, 5);
    transform.setRotation(0, tempSin * 180, 0);
    // transform.setScale(0.5f * tempSin, 0.5f * tempSin, 0.5f * tempSin);
  }

  public void render() {

    RenderUtil.setClearColor(Transform.getCamera().getPosition().divide(2048f).absolute());
    shader.bind();
    shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
    mesh.draw();
  }
}
