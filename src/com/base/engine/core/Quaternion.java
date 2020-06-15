/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;


/**
 *
 * @author kmne68
 */
public class Quaternion {
    
    private float x;
    private float y;
    private float z;
    private float w;
    
    
    public Quaternion(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    
    public float length() 
    {
        return (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }
    
    
    public Quaternion normalize()
    {
        float length = length();
        
        return new Quaternion( x / length, y / length, z / length, w / length );        
    }

    
    public Quaternion conjugate()
    {
        return new Quaternion(-x, -y, -z, w);
    }
    
    
    public Quaternion multiplyQuaternion(Quaternion r)
    {
        float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();
        
        return new Quaternion(x_, y_, z_, w_);
    }
    
    
    public Quaternion multiplyVector(Vector3f r)
    {
        float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ =  w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ =  w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ =  w * r.getZ() + x * r.getY() - y * r.getX();
        
        return new Quaternion(x_, y_, z_, w_);
    }
    
    public Matrix4f toRotationMatrix() {
      
      return null;
    }
    
    public Vector3f getForward() {
      
      return new Vector3f(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0f - 2.0f * (x * x + y * y));
    }
    
    public Vector3f getBack() {
      
      return new Vector3f(-2.0f * (x * z - w * y), -2.0f * (y * z + w * x), -(1.0f - 2.0f * (x * x + y * y)));
    }
    
    public Vector3f getUp() {
      
      return new Vector3f(2.0f *  (x * y + w * z), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x));
    }
    
    public Vector3f getDown() {
      
      return new Vector3f(-2.0f * (x * y + w * z), -(1.0f - 2.0f * (x * x + z * z)), -2.0f * (y * z - w * x));
    }
    
    public Vector3f getRight() {
      
      return new Vector3f(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));
    }
    
    public Vector3f getLeft() {
      
      return new Vector3f(-(1.0f - 2.0f * (y * y + z * z)), -2.0f * (x * y - w * z), -2.0f * (x * z + w * y));
    }
    
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }    
}
