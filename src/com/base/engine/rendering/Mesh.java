/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.core.BufferUtil;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.meshloader.IndexedModel;
import com.base.engine.rendering.meshloader.OBJModel;
import com.base.engine.rendering.resourcemanagement.MeshResource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author kmne68
 */
public class Mesh {
    
    private MeshResource buffers;
    private int size;   // How much space vbo points to
    
    
    public Mesh(String fileName) {
      
      initMeshData();
      loadMesh(fileName);
    }
    
    public Mesh(Vertex[] vertices, int[] indices) {
      
      this(vertices, indices, false);
    }
    
    
    public Mesh(Vertex[] vertices, int[] indices, boolean calculateNormals) {
      
      initMeshData();
      addVertices(vertices, indices, calculateNormals);
    }
    
    
    private void initMeshData() {
      
        buffers = new MeshResource();
        size = 0;      
    }

    
    private void addVertices(Vertex[] vertices, int[] indices, boolean calculateNormals)
    {
      
      if(calculateNormals) {
        calculateNormals(vertices, indices);
      }
      
        size = indices.length; // * Vertex.SIZE;
        
        glBindBuffer(GL_ARRAY_BUFFER, buffers.getVbo());
        glBufferData(GL_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(vertices), GL_STATIC_DRAW);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffers.getIbo());
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(indices), GL_STATIC_DRAW);
    }    
    
    public void draw()
    {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        
        glBindBuffer(GL_ARRAY_BUFFER, buffers.getVbo());
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0); 
        
        // The final parameter (12) is how many bytes into the memory location
        // we will find the texture coordinate data
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);
        
        // Not used after as of installment #15
        // glDrawArrays(GL_TRIANGLES, 0, size);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffers.getIbo());
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
    }
    
    
    private void calculateNormals(Vertex[] vertices, int[] indices) {
      
      for(int i = 0; i < indices.length; i += 3) {
        int i0 = indices[i];
        int i1 = indices[i + 1];
        int i2 = indices[i + 2];
        
        Vector3f v1 = vertices[i1].getPosition().subtract( vertices[i0].getPosition() );
        Vector3f v2 = vertices[i2].getPosition().subtract( vertices[i0].getPosition() );
        
        Vector3f normal = v1.crossProduct(v2).normalize();
        
        vertices[i0].setNormal( vertices[i0].getNormal().add(normal) );
        vertices[i1].setNormal( vertices[i1].getNormal().add(normal) );
        vertices[i2].setNormal( vertices[i2].getNormal().add(normal) );
      }
      
      for(int i = 0; i < vertices.length; i++) {
        
        vertices[i].setNormal(vertices[i].getNormal().normalize() );
      }
    }
    
    
    private Mesh loadMesh(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length -1];        
        
        if(!ext.equals("obj"))
        {
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }
        
        OBJModel monkeyTest = new OBJModel("./res/models/" + fileName);
        IndexedModel model = monkeyTest.toIndexedModel();
        model.calculateNormals();
        
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        
        for(int i = 0; i < model.getPositions().size(); i++) {
          
          vertices.add(new Vertex(model.getPositions().get(i),
            model.getTextureCoordinates().get(i),
            model.getNormals().get(i)));
        }
          
      Vertex[] vertexData = new Vertex[vertices.size()];
      vertices.toArray(vertexData);

      Integer[] indexData = new Integer[model.getIndices().size()];
      model.getIndices().toArray(indexData);
      
      addVertices(vertexData, BufferUtil.toIntArray(indexData), false );        

        return null;
    }
    
}
