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
public class Vector2f {
    
    private float x;
    private float y;
    
    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    
    // The length of the vector via Pythagorean Theorem
    public float length()
    {
        return (float) Math.sqrt(x * x + y * y);
    }
    
    
    // The dot product of this vector and another
    public float dot(Vector2f r)
    {
        return x *  r.getX() + y * r.getY();
    }
    
    
    public Vector2f normalize()
    {
        float length = length();
        
        x /= length;
        y /= length;
        
        return this;
    }
    
    
    public Vector2f rotate(float angle)
    {
        float radians = (float)Math.toRadians(angle);
        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);
        
        return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));       

    }
    
    
    public Vector2f add(Vector2f r)
    {
        return new Vector2f(x + r.getX(), y + r.getY());
    }
    
    
    public Vector2f add(float r)
    {
        return new Vector2f(x + r, y + r);
    }
        
    public Vector2f subtract(Vector2f r)
    {
        return new Vector2f(x - r.getX(), y - r.getY());
    }
    
    public Vector2f subtract(float r)
    {
        return new Vector2f(x - r, y - r);
    }
        
        
    public Vector2f multiply(Vector2f r)
    {
        return new Vector2f(x * r.getX(), y * r.getY());
    }
    
        
    public Vector2f multiply(float r)
    {
        return new Vector2f(x * r, y * r);
    }
    
    
    public Vector2f divide(Vector2f r)
    {
        return new Vector2f(x / r.getX(), y / r.getY());
    }

    
    public Vector2f divide(float r)
    {
        return new Vector2f(x / r, y / r);
    }    
    

    public String toString()
    {
        return "(" + x + ", " + y + ")";
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
    
}
