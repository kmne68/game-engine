/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;

/**
 *
 * @author kmne68
 */
public class Vertex {

  public static final int SIZE = 8;

  private Vector3f position;
  private Vector2f textureCoordinate;
  private Vector3f normal;

  public Vertex(Vector3f position) {
    this(position, new Vector2f(0, 0));
  }

  public Vertex(Vector3f position, Vector2f textureCoordinate) {
    this(position, textureCoordinate, new Vector3f(0, 0, 0));
  }

  public Vertex(Vector3f position, Vector2f textureCoordinate, Vector3f normal) {
    this.position = position;
    this.textureCoordinate = textureCoordinate;
    this.normal = normal;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public Vector2f getTextureCoordinate() {
    return textureCoordinate;
  }

  public void setTextureCoordinate(Vector2f textureCoordinate) {
    this.textureCoordinate = textureCoordinate;
  }

  public Vector3f getNormal() {
    return normal;
  }

  public void setNormal(Vector3f normal) {
    this.normal = normal;
  }

}
