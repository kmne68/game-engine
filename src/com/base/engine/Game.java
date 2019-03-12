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

    public Game() {
        
        mesh = new Mesh();
        
        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
                                      new Vertex(new Vector3f(0, 1, 0)),            
                                      new Vertex(new Vector3f(1, -1, 0))};
                        

        mesh.addVertices(data);
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

    public void update() {

    }

    public void render() {
        
        mesh.draw();
    }
}
