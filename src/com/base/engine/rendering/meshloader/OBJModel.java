/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.meshloader;

import com.base.engine.core.BufferUtil;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kmne6
 */
public class OBJModel {

  private ArrayList<Vector3f> positions;
  private ArrayList<Vector2f> textureCoordinates;
  private ArrayList<Vector3f> normals;
  private ArrayList<OBJIndex> indices;

  private boolean hasTextureCoordinates;
  private boolean hasNormals;

  public OBJModel(String fileName) {

    positions = new ArrayList<Vector3f>();
    textureCoordinates = new ArrayList<Vector2f>();
    normals = new ArrayList<Vector3f>();
    indices = new ArrayList<OBJIndex>();
    hasTextureCoordinates = false;
    hasNormals = false;

    BufferedReader meshReader = null;

    try {
      meshReader = new BufferedReader(new FileReader(fileName));
      String line;

      while ((line = meshReader.readLine()) != null) {
        String[] tokens = line.split(" ");
        tokens = BufferUtil.removeEmptyStrings(tokens);

        if (tokens.length == 0 || tokens[0].equals("#")) {
          continue;
        } else if (tokens[0].equals("v")) {   // v = vertex
          positions.add(new Vector3f(Float.valueOf(tokens[1]),
                  Float.valueOf(tokens[2]),
                  Float.valueOf(tokens[3])));
        } else if (tokens[0].equals("vt")) {  // vt = vertex texture?

          textureCoordinates.add(new Vector2f(Float.valueOf(tokens[1]),
                  Float.valueOf(tokens[2])));
        } else if (tokens[0].equals("vn")) {  // vn = vertext normal
          normals.add(new Vector3f(Float.valueOf(tokens[1]),
                  Float.valueOf(tokens[2]),
                  Float.valueOf(tokens[3])));
        } else if (tokens[0].equals("f")) // f = face
        {
          for(int i = 0; i < tokens.length - 3; i++ )
          {
            indices.add(parseOBJIndex(tokens[1]));
            indices.add(parseOBJIndex(tokens[2 + i]));
            indices.add(parseOBJIndex(tokens[3 + i]));
          }
        }
      }

      meshReader.close();

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

//  public ArrayList<Vector3f> getPositions() {
//    return positions;
//  }
//
//  public ArrayList<Vector2f> getTextureCoordinates() {
//    return textureCoordinates;
//  }
//
//  public ArrayList<Vector3f> getNormals() {
//    return normals;
//  }
//
//  public ArrayList<OBJIndex> getIndices() {
//    return indices;
//  }
  
  public IndexedModel toIndexedModel() {
    
    IndexedModel result = new IndexedModel();
    IndexedModel normalModel = new IndexedModel();
    HashMap<OBJIndex, Integer> resultIndexMap = new HashMap<OBJIndex, Integer>();
    HashMap<Integer, Integer> normalIndexMap = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
    
    for(int i = 0; i < indices.size(); i++) {
      OBJIndex currentIndex = indices.get(i);
      
      Vector3f currentPosition = positions.get(currentIndex.vertexIndex);
      Vector2f currentTextureCoordinate;
      Vector3f currentNormal;
      
      if(hasTextureCoordinates)
        currentTextureCoordinate = textureCoordinates.get(currentIndex.textureCoordinateIndex);
      else
        currentTextureCoordinate = new Vector2f(0, 0);
      
      if(hasNormals)
        currentNormal = normals.get(currentIndex.normalIndex);
      else
        currentNormal = new Vector3f(0, 0, 0);
      
    //  int modelVertexIndex = -1;
      
      Integer modelVertexIndex = resultIndexMap.get(currentIndex);
      
      if(modelVertexIndex == null)
      {
        
        modelVertexIndex = result.getPositions().size();
//        resultIndexMap.put(currentIndex, result.getPositions().size());
        resultIndexMap.put(currentIndex, modelVertexIndex);
        
        result.getPositions().add(currentPosition);
        result.getTextureCoordinates().add(currentTextureCoordinate);
        if(hasNormals)
          result.getNormals().add(currentNormal);
        
      }
      
      Integer normalModelIndex = normalIndexMap.get(currentIndex.vertexIndex);
      
      if(normalModelIndex == null) {
        
        normalModelIndex = normalModel.getPositions().size();
        normalIndexMap.put(currentIndex.vertexIndex, normalModelIndex);
        
        normalModel.getPositions().add(currentPosition);
        normalModel.getTextureCoordinates().add(currentTextureCoordinate);
        normalModel.getNormals().add(currentNormal);
        
      }
      
      result.getIndices().add(modelVertexIndex);
      normalModel.getIndices().add(normalModelIndex);
      indexMap.put(modelVertexIndex, normalModelIndex);
    }
    
    if(!hasNormals) {
      // NOTE: for completely accurate normals, export them with your model
      normalModel.calculateNormals();
      
      for(int i = 0; i < result.getPositions().size(); i++) {
        
        result.getNormals().add(normalModel.getNormals().get(indexMap.get(i)));
        // result.getNormals().get(i).set(normalModel.getNormals().get(indexMap.get(i)));
      }
      
    }
    
    return result;
    
  }

  /**
   * PRIVATE METHODS
   */
  private OBJIndex parseOBJIndex(String token) {

    String[] values = token.split("/");

    OBJIndex result = new OBJIndex();
    result.vertexIndex = Integer.parseInt(values[0]) - 1;

    if (values.length > 1) {
      hasTextureCoordinates = true;
      result.textureCoordinateIndex = Integer.parseInt(values[1]) - 1;

      if (values.length > 2) {
        hasNormals = true;
        result.normalIndex = Integer.parseInt(values[2]) - 1;
      }
    }

    return result;

  }

}
