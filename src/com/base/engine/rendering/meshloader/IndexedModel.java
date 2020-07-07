/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering.meshloader;

import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Vertex;
import java.util.ArrayList;

/**
 *
 * @author kmne6
 */
public class IndexedModel {

  private ArrayList<Vector3f> positions;
  private ArrayList<Vector2f> textureCoordinates;
  private ArrayList<Vector3f> normals;
  private ArrayList<Integer> indices;

  public IndexedModel() {

    positions = new ArrayList<Vector3f>();
    textureCoordinates = new ArrayList<Vector2f>();
    normals = new ArrayList<Vector3f>();
    indices = new ArrayList<Integer>();

  }

  public void calculateNormals() {

    for (int i = 0; i < indices.size(); i += 3) {
      int i0 = indices.get(i);
      int i1 = indices.get(i + 1);
      int i2 = indices.get(i + 2);

      Vector3f v1 = positions.get(i1).subtract(positions.get(i0));
      Vector3f v2 = positions.get(i2).subtract(positions.get(i0));

      Vector3f normal = v1.crossProduct(v2).normalize();

      normals.get(i0).set(normals.get(i0).add(normal));
      normals.get(i1).set(normals.get(i1).add(normal));
      normals.get(i2).set(normals.get(i2).add(normal));
    }

    for (int i = 0; i < positions.size(); i++) {

      normals.get(i).set(normals.get(i).normalize());
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

  public ArrayList<Integer> getIndices() {
    return indices;
  }

}
