/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author kmne68
 */
public class Vertex {
    
    public static final int SIZE = 5;
    
    private Vector3f position;
    private Vector2f textureCoordinate;
    
    
    public Vertex(Vector3f position, Vector2f textureCoordinate)
    {
        this.position = position;
        this.textureCoordinate = textureCoordinate;
    }
    
    public Vertex(Vector3f position)
    {
        this(position, new Vector2f(0, 0));
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
    
    
}
