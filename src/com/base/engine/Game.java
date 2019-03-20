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
    private Transform transform;

    public Game() {
        
        mesh = new Mesh();
        shader = new Shader();
        
        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
                                      new Vertex(new Vector3f(0, 1, 0)),            
                                      new Vertex(new Vector3f(1, -1, 0))};
                        

        mesh.addVertices(data);
        
        transform = new Transform();
        
        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        shader.compileShader();
        
        // shader.addUniform("uniformFloat");
        shader.addUniform("transform");
    }

    public void input() {
        if (Input.getKeyDown(Keyboard.KEY_UP)) {
            System.out.println("Pressed up");
        }
        if (Input.getKeyUp(Keyboard.KEY_UP)) {
            System.out.println("Released up");
        }

        if (Input.getMouseDown(1)) {
            System.out.println("Pressed mouse right at " + Input.getMousePosition().toString());
        }
        if (Input.getMouseUp(1)) {
            System.out.println("Released mouse right");
        }
    }

    
    // Temporary variable for testing uniform variables
    float temp = 0.0f;
    // float tempAmount = 0.0f;
    
    public void update() {
        
        temp += Time.getDelta();
        
        float tempSin = (float)Math.sin(temp);
        // tempAmount = (float)Math.sin(temp);
        // shader.setUniformf("uniformFloat", (float)Math.abs(Math.sin(temp)));
        
        transform.setTranslation((float)Math.sin(temp), 0, 0);
        transform.setRotation(0, 0, (float)Math.sin(temp) * 180);        
        transform.setScale(tempSin, tempSin, tempSin);
    }

    public void render() {
        
        shader.bind();
        shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
}
