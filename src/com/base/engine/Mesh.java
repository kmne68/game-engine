/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author kmne68
 */
public class Mesh {
    
    private int vbo;    // A pointer (handle)
    private int ibo;    // Described as an array of integers on the graphics card
    private int size;   // How much space vbo points to
    
    public Mesh()
    {
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = 0;
    }
    
    public void addVertices(Vertex[] vertices, int[] indices)
    {
        size = indices.length; // * Vertex.SIZE;
        
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(vertices), GL_STATIC_DRAW);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(indices), GL_STATIC_DRAW);
    }    
    
    public void draw()
    {
        glEnableVertexAttribArray(0);
        
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        
        // Not used after as of installment #15
        // glDrawArrays(GL_TRIANGLES, 0, size);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
        
        glDisableVertexAttribArray(0);
    }
}
