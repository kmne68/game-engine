/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.util.HashMap;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

/**
 *
 * @author kmne68
 */
public class Shader {

  private int program;
  private HashMap<String, Integer> uniforms;

  public Shader() {
    
    System.out.println("*** Shader() ***");
    
    program = glCreateProgram();
    uniforms = new HashMap<String, Integer>();

    if (program == 0) {
      System.err.println("Shader creation failed: Could not find valid memor location in constructor.");
      System.exit(1);
    }
  }

  public void addVertexShader(String text) {
    System.out.println("*** Shader().addVertexShader() ***");
    addProgram(text, GL_VERTEX_SHADER);
  }

  public void addGeometryShader(String text) {
    System.out.println("*** Shader().addGeometryShader() ***");
    addProgram(text, GL_GEOMETRY_SHADER);
  }

  public void addFragmentShader(String text) {
    System.out.println("*** Shader().addFragmentShader() ***");
    addProgram(text, GL_FRAGMENT_SHADER);
  }

  /**
   * Adds a uniform variable for use with shader files
   *
   * @param uniform
   */
  public void addUniform(String uniform) {
    System.out.println("*** Shader().addUniform() ***");
    int uniformLocation = glGetUniformLocation(program, uniform);

    if (uniformLocation == 0xFFFFFF) {
      System.out.println("Error: could not find uniform " + uniform);
      new Exception().printStackTrace();
      System.exit(1);
    }

    uniforms.put(uniform, uniformLocation);
  }

  public void bind() {
    System.out.println("*** Shader().bind() ***");
    glUseProgram(program);
  }

  public void compileShader() {
    System.out.println("*** Shader().compileShader() ***");
    glLinkProgram(program);

    if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
      System.err.println(glGetProgramInfoLog(program, 1024));
      System.exit(1);
    }

    glValidateProgram(program);

    if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
      System.err.println(glGetProgramInfoLog(program, 1024));
      System.exit(1);
    }
  }

  public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
    System.out.println("*** Shader().updateUniforms() ***");
  }

  private void addProgram(String text, int type) {
    System.out.println("*** Shader().addProgram() ***");
    
    int shader = glCreateShader(type);
    
    System.out.println("*** shader = " + shader + " ***");
    if (shader == 0) {
      System.err.println("Shader creation failed. Could not find valid memory location in constructor.");
      System.exit(1);
    }
    System.out.println("*** shader.addProgram() after if() ***");
    glShaderSource(shader, text);
    System.out.println("*** shader.addProgram() after glShaderSource() ***");
    
    glCompileShader(shader);
    System.out.println("*** shader.addProgram() after glCompileShader() ***");
    System.out.println("*** " + GL_COMPILE_STATUS + " ***");
    System.out.println("*** glGetShader(shader, GL_COMPILE_STATUS = " + glGetShaderi(shader, GL_COMPILE_STATUS) + " ***");
    
    if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
      System.out.println("*** inside if(glGetShaderi()... ***");
      System.err.println(glGetShaderInfoLog(shader, 1024));
      System.out.println("*** inside if(glGetShaderi() after error message... ***");
      System.exit(1);
    }

    glAttachShader(program, shader);
  }

  public void setUniformi(String uniformName, int value) {
    glUniform1i(uniforms.get(uniformName), value);
  }

  public void setUniformf(String uniformName, float value) {
    glUniform1f(uniforms.get(uniformName), value);
  }

  public void setUniform(String uniformName, Vector3f value) {
    glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
  }

  public void setUniform(String uniformName, Matrix4f value) {
    glUniformMatrix4(uniforms.get(uniformName), true, BufferUtil.createFlippedBuffer(value));
  }

}
