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
    
    boolean mouseLocked = false;
    
    Vector2f centerPosition = new Vector2f(
            Window.getWidth() / 2, Window.getHeight() / 2 );
    
    
    public Camera()
    {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
        System.out.println("*** Camera() ***");
    }
    

    public Camera(Vector3f position, Vector3f forward, Vector3f up) {
      
        this.position = position;
        this.forward = forward.normalize();
        this.up = up.normalize();
        
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
      return forward.crossProduct(up).normalize();
    }
    
    
    public Vector3f getRight()
    {
      return up.crossProduct(forward).normalize();
    }
    
    
    public void input()
    {  
        float sensitivity = 0.5f;
        float moveAmount = (float)(10 * Time.getDelta());
   //     float rotationAmount = (float)(100 * Time.getDelta());

        if(Input.getKey(Input.KEY_ESCAPE)) {
          Input.setCursor(true);
          mouseLocked = false;
        }
        
        if(Input.getMouseDown(0)) {
          Input.setMousePosition(centerPosition);
          Input.setCursor(false);
          mouseLocked = true;
        }
        
        if(Input.getKey(Input.KEY_W))
            move(getForward(), moveAmount);
        if(Input.getKey(Input.KEY_S))
            move(getForward(), -moveAmount);        
        if(Input.getKey(Input.KEY_A))
            move(getLeft(), moveAmount);        
        if(Input.getKey(Input.KEY_D))
            move(getRight(), moveAmount);  
        
//        if(Input.getKey(Input.KEY_UP))
//            rotateX(-rotationAmount);
//        if(Input.getKey(Input.KEY_DOWN))
//            rotateX(rotationAmount);
//        if(Input.getKey(Input.KEY_LEFT))
//            rotateY(-rotationAmount);
//        if(Input.getKey(Input.KEY_RIGHT))
//            rotateY(rotationAmount);
        
        if(mouseLocked) {
          Vector2f deltaPosition = Input.getMousePosition().subtract(centerPosition);
          
          boolean rotateY = deltaPosition.getX() != 0;
          boolean rotateX = deltaPosition.getY() != 0;
          
          if(rotateY)
            rotateY(deltaPosition.getX() * sensitivity);
          if(rotateX)
            rotateX(-deltaPosition.getY() * sensitivity);
          
          if(rotateY || rotateX)
            Input.setMousePosition( new Vector2f( Window.getWidth() / 2, Window.getHeight() / 2 ) );
        }
        
    }    
    
    public void rotateY(float angle)
    {
      Vector3f hAxis = yAxis.crossProduct(forward).normalize();
      
      forward = forward.rotate(angle, yAxis).normalize();
      
      up = forward.crossProduct(hAxis).normalize();
      
    }
    
    
    public void rotateX(float angle)
    {
      Vector3f hAxis = yAxis.crossProduct(forward).normalize();
      
      forward = forward.rotate(angle, hAxis).normalize();
      
      up = forward.crossProduct(hAxis).normalize();
      
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
