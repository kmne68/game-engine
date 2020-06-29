/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.meshloader;

import com.base.engine.core.BufferUtil;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Vertex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author kmne6
 */
public class OBJModel {
  
  private ArrayList<Vector3f> positions;
  private ArrayList<Vector2f> textureCoordinates;
  private ArrayList<Vector3f> normals;
  private ArrayList<OBJIndex>  indices;
  
  
  public OBJModel(String fileName) {
    
    positions = new ArrayList<Vector3f>();
    textureCoordinates = new ArrayList<Vector2f>();
    normals = new ArrayList<Vector3f>();
    indices = new ArrayList<OBJIndex>();   
    
            BufferedReader meshReader = null;
        
        try
        {
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;
            
            while((line = meshReader.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                tokens = BufferUtil.removeEmptyStrings(tokens);
                
                if(tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                else if(tokens[0].equals("v"))
                {
                    positions.add(new Vector3f(Float.valueOf(tokens[1]),
                                                        Float.valueOf(tokens[2]),
                                                        Float.valueOf(tokens[3])));
                }
                else if( tokens[0].equals( "vt" ) ) {
                  
                    textureCoordinates.add(new Vector2f(Float.valueOf(tokens[1]),
                                                        Float.valueOf(tokens[2])));
                }
//                else if(tokens[0].equals("f")) 
//                {
//                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
//                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
//                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
//                    
//                    if(tokens.length > 4)
//                    {
//                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
//                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
//                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
//                    }
//                }
            }
            
            meshReader.close();
            
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);
            
            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);
            
            addVertices(vertexData, BufferUtil.toIntArray(indexData), true);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }        

        return null;
    }
    
  }

  public ArrayList<Vector3f> getPositions() {
    return positions;
  }

  public ArrayList<Vector2f> getTextureCoordinates() {
    return textureCoordinates;
  }

  public ArrayList<Vector3f> getNormals() {
    return normals;
  }

  public ArrayList<OBJIndex> getIndices() {
    return indices;
  }
  
}
