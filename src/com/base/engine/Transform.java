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
public class Transform {
    
    private static float zNear; // how close an object can be before it gets clipped
    private static float zFar;  // how far an object can be before it gets clipped
    private static float screenWidth;
    private static float screenHeight;
    private static float fieldOfView;   // for perspective projection
    private static Camera camera;

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera aCamera) {
        camera = aCamera;
    }
    
    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;

    
    public Transform() {
        
        translation = new Vector3f(0, 0, 0);
        rotation    = new Vector3f(0, 0, 0);
        scale       = new Vector3f(1, 1, 1);
    }
    
    
    public Matrix4f getTransformation()
    {
        Matrix4f translationMatrix  = new Matrix4f().initTranslation(translation.getX(),
                                                                    translation.getY(),
                                                                    translation.getZ());
        Matrix4f rotationMatrix     = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
        Matrix4f scaleMatrix        = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
        
        // Apply scale transformation first
        return translationMatrix.multiplyMatrix(rotationMatrix.multiplyMatrix(scaleMatrix));
    }
    
    
    public Matrix4f getProjectedTransformation()
    {
        Matrix4f transformationMatrix = getTransformation();
        Matrix4f projectionMatrix = new Matrix4f().initProjection(fieldOfView, zNear, zNear, zNear, zFar);
        Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(
                                        -camera.getPosition().getX(), -camera.getPosition().getY(), -camera.getPosition().getZ());
        
        return projectionMatrix.multiplyMatrix(cameraRotation.multiplyMatrix(cameraTranslation.multiplyMatrix(transformationMatrix)));
    }
    

    public Vector3f getTranslation() {
        return translation;
    }
    
    
    public static void setProjection(float fov, float width, float height, float zNear, float zFar)
    {
        Transform.fieldOfView = fov;
        Transform.screenWidth = width;
        Transform.screenHeight = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
        
    }
    

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }
    
    
    public void setTranslation(float x, float y, float z)
    {
        this.translation = new Vector3f(x, y, z);
    }
    
    
    
    public Vector3f getRotation() {
        return rotation;
    }
    

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
    
    
    public void setRotation(float x, float y, float z)
    {
        this.rotation = new Vector3f(x, y, z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    
    
    public void setScale(float x, float y, float z)
    {
        this.scale = new Vector3f(x, y, z);
    }
    
}
