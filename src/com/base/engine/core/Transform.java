/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import com.base.engine.rendering.Camera;

/**
 *
 * @author kmne6
 */
public class Transform {

    private Vector3f position;
    private Vector3f scale;
    private Quaternion rotation;

    
    public Transform() {
        
        position = new Vector3f(0, 0, 0);
        scale       = new Vector3f(1, 1, 1);
        rotation    = new Quaternion(0, 0, 0, 1);
    }
    
    
    public Matrix4f getTransformation()
    {
        Matrix4f translationMatrix  = new Matrix4f().initializeTranslation(position.getX(),
                                                                    position.getY(),
                                                                    position.getZ());
        
        Matrix4f rotationMatrix     =  rotation.toRotationMatrix();
        Matrix4f scaleMatrix        = new Matrix4f().initializeScale(scale.getX(), scale.getY(), scale.getZ());
        
        // Apply scale transformation first
        return translationMatrix.multiplyMatrix(rotationMatrix.multiplyMatrix(scaleMatrix));
    }
    

    public Vector3f getPosition() {
        return position;
    }
    

    public void setPosition(Vector3f position) {
        this.position = position;
    }
    
    
    public void setPosition(float x, float y, float z)
    {
        this.position = new Vector3f(x, y, z);
    }
    
    
    
    public Quaternion getRotation() {
        return rotation;
    }
    

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }
    
    
    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    
}
