/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author kmne6
 */
public class Camera {
    
    public static final Vector3f yAxis = new Vector3f(0, 1, 0);
    
    private Vector3f position;
    private Vector3f forward;
    private Vector3f up;
    
    
    public Camera()
    {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
    }
    

    public Camera(Vector3f position, Vector3f forward, Vector3f up) {
        this.position = position;
        this.forward = forward;
        this.up = up;
        
        up.normalize();
        forward.normalize();
    }
    
    
    public void move(Vector3f direction, float amount)
    {
        position = position.add(direction.multiply(amount));
    }

    /**
     * Get normalized vector facing left
     */
    public Vector3f getLeft()
    {
        Vector3f left = up.crossProduct(forward);
        left.normalize();
        return left;
    }
    
    
    public Vector3f getRight()
    {
        Vector3f right = forward.crossProduct(up);
        right.normalize();
        return right;
    }
    
    
    public void rotateY(float angle)
    {
        Vector3f horizontalAxis = yAxis.crossProduct(forward);
        horizontalAxis.normalize();    
        
        forward.rotate(angle, yAxis);
        forward.normalize();
        
        up = forward.crossProduct(horizontalAxis);
        up.normalize();
    }
    
    
    public void rotateX(float angle)
    {
        Vector3f horizontalAxis = yAxis.crossProduct(forward);
        horizontalAxis.normalize();
        
        forward.rotate(angle, horizontalAxis);
        forward.normalize();
        
        up = forward.crossProduct(horizontalAxis);
        up.normalize();
    }
    
    
    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }    
}