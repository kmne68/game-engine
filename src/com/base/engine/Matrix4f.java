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
public class Matrix4f {
    
    private float[][] m;
    
    public Matrix4f()
    {
        m = new float[4][4];
    }
    
    
    // Sets matrix m equal to the identity matrix
    public Matrix4f initIdentity()
    {
        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = 0;
        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = 0;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = 0;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;     
        
        return this;
    }
    
    
    public Matrix4f initTranslation(float x, float y, float z)
    {
        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = x;
        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = y;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = z;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;     
        
        return this;
    }   
    
    /**
     * 
     * @param fov       // field of view
     * @param width     // screen width
     * @param height    // screen height
     * @param zNear     // near limit on Z axis
     * @param zFar      // far limit on Z axis
     * @return 
     */
    public Matrix4f initProjection(float fov, float width, float height, float zNear, float zFar)
    {
        float aspectRatio = width / height; // used because screen height and width aren't equal 
        float tanHalfMathFOV = (float) Math.tan((Math.toRadians(fov / 2)));
        float zRange = zNear - zFar;
        
        // Divide by each point's distance to center i.e. tanHalfMathFOV
        // x values
        m[0][0] = 1.0f / tanHalfMathFOV * aspectRatio;
        m[0][1] = 0;    
        m[0][2] = 0;    
        m[0][3] = 0;
        
        // y values
        m[1][0] = 0;    
        m[1][1] = 1 / tanHalfMathFOV;    
        m[1][2] = 0;    
        m[1][3] = 0;
        
        // z values
        m[2][0] = 0;    
        m[2][1] = 0;    
        m[2][2] = (-zNear - zFar)/zRange;    
        m[2][3] = 2 * zFar * zNear / zRange;
        
        // w values
        m[3][0] = 0;    
        m[3][1] = 0;    
        m[3][2] = 1;    // z overrides w and OpenGL will divide it out later
        m[3][3] = 0;     
        
        return this;
    }
    
    
    public Matrix4f initCamera(Vector3f forward, Vector3f up)
    {
        Vector3f f = forward;
        f.normalize();
        
        Vector3f r = up;
        r.normalize();
        r = r.crossProduct(f);
        
        Vector3f u = f.crossProduct(r);
        
        m[0][0] = r.getX();    m[0][1] = r.getY();    m[0][2] = r.getZ();    m[0][3] = 0;
        m[1][0] = u.getX();    m[1][1] = u.getY();    m[1][2] = u.getZ();    m[1][3] = 0;
        m[2][0] = f.getX();    m[2][1] = f.getY();    m[2][2] = f.getZ();    m[2][3] = 0;
        m[3][0] = 0;           m[3][1] = 0;           m[3][2] = 0;           m[3][3] = 1;     
        
        return this;
    }
    
    
    /**
     * Rotate in each two-dimensional plane then multiply the products to get
     * three-dimensional rotation.
     * 
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public Matrix4f initRotation(float x, float y, float z)
    {
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
        Matrix4f rz = new Matrix4f();
        
        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);        
        
        rz.m[0][0] = (float)Math.cos(z);    rz.m[0][1] = -(float)Math.sin(z);   rz.m[0][2] = 0;                     rz.m[0][3] = 0;
        rz.m[1][0] = (float)Math.sin(z);    rz.m[1][1] = (float)Math.cos(z);    rz.m[1][2] = 0;                     rz.m[1][3] = 0;
        rz.m[2][0] = 0;                     rz.m[2][1] = 0;                     rz.m[2][2] = 1;                     rz.m[2][3] = 0;
        rz.m[3][0] = 0;                     rz.m[3][1] = 0;                     rz.m[3][2] = 0;                     rz.m[3][3] = 1;     

        rx.m[0][0] = 1;                     rx.m[0][1] = 0;                     rx.m[0][2] = 0;                     rx.m[0][3] = 0;
        rx.m[1][0] = 0;                     rx.m[1][1] = (float)Math.cos(x);    rx.m[1][2] = -(float)Math.sin(x);   rx.m[1][3] = 0;
        rx.m[2][0] = 0;                     rx.m[2][1] = (float)Math.sin(x);    rx.m[2][2] = (float)Math.cos(x);    rx.m[2][3] = 0;
        rx.m[3][0] = 0;                     rx.m[3][1] = 0;                     rx.m[3][2] = 0;                     rx.m[3][3] = 1;  
        
        
        ry.m[0][0] = (float)Math.cos(y);    ry.m[0][1] = 0;                     ry.m[0][2] = -(float)Math.sin(y);    ry.m[0][3] = 0;
        ry.m[1][0] = 0;                     ry.m[1][1] = 1;                     ry.m[1][2] = 0;                      ry.m[1][3] = 0;
        ry.m[2][0] = (float)Math.sin(y);    ry.m[2][1] = 0;                     ry.m[2][2] = (float)Math.cos(y);;    ry.m[2][3] = 0;
        ry.m[3][0] = 0;                     ry.m[3][1] = 0;                     ry.m[3][2] = 0;                      ry.m[3][3] = 1;  

         m = rz.multiplyMatrix(ry.multiplyMatrix(rx)).getM();
         
        return this;
    }    
    
    
    public Matrix4f initScale(float x, float y, float z)
    {
        m[0][0] = x;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = 0;    // row calculates final x component
        m[1][0] = 0;    m[1][1] = y;    m[1][2] = 0;    m[1][3] = 0;    // row calculates final y component
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = z;    m[2][3] = 0;    // row calculates final z component
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;    // row calculates final w component
        
        return this;
    }  
    
    
    public Matrix4f multiplyMatrix(Matrix4f r)
    {
        Matrix4f result = new Matrix4f();
        
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
            {
                result.set(i, j, m[i][0] * r.get(0, j) +
                        m[i][1] * r.get(1, j) +
                        m[i][2] * r.get(2, j) +
                        m[i][3] * r.get(3, j));
            }
        
        return result;
    }

    
    public float get(int x, int y)
    {
        return m[x][y];
    }
    
    
    public void set(int x, int y, float value)
    {
        m[x][y] = value;
    }
    

    public float[][] getM() {
        return m;
    }
   
    
    public void setM(float[][] m) {
        this.m = m;
    }
    
}
