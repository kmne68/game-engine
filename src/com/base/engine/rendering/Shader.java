/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.rendering;

import com.base.engine.core.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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

    program = glCreateProgram();
    uniforms = new HashMap<String, Integer>();

    if (program == 0) {
      System.err.println("Shader creation failed: Could not find valid memor location in constructor.");
      System.exit(1);
    }
  }

  public void bind() {
    glUseProgram(program);
  }

  public void addVertexShaderFromFile(String text) {
    addProgram(loadShader(text), GL_VERTEX_SHADER);
  }

  public void addGeometryShaderFromFile(String text) {
    addProgram(loadShader(text), GL_GEOMETRY_SHADER);
  }

  public void addFragmentShaderFromFile(String text) {
    addProgram(loadShader(text), GL_FRAGMENT_SHADER);
  }

  public void addVertexShader(String text) {
    addProgram(text, GL_VERTEX_SHADER);
  }

  public void addGeometryShader(String text) {
    addProgram(text, GL_GEOMETRY_SHADER);
  }

  public void addFragmentShader(String text) {
    addProgram(text, GL_FRAGMENT_SHADER);
  }

  public void setAttributeLocation(String attributeName, int location) {

    glBindAttribLocation(program, location, attributeName);
  }

  public void addAllAttributes(String shaderText) {

    final String ATTRIBUTE_KEYWORD = "attribute";
    int attributeStartLocation = shaderText.indexOf(ATTRIBUTE_KEYWORD);
    int attributeNumber = 0;

    while (attributeStartLocation != -1) {

      int begin = attributeStartLocation + ATTRIBUTE_KEYWORD.length() + 1;
      int end = shaderText.indexOf(";", begin);

      String attributeLine = shaderText.substring(begin, end);
      String attributeName = attributeLine.substring(attributeLine.indexOf(' ') + 1, attributeLine.length());

      setAttributeLocation(attributeName, attributeNumber);
      attributeNumber++;

      attributeStartLocation = shaderText.indexOf(ATTRIBUTE_KEYWORD, attributeStartLocation + ATTRIBUTE_KEYWORD.length());
    }
  }
  
  
  private HashMap<String, ArrayList<String> > findUniformStructs(String shaderText) {
    
    HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
    
    final String STRUCT_KEYWORD = "struct";
    int structStartLocation = shaderText.indexOf(STRUCT_KEYWORD);

    while (structStartLocation != -1) {

      int nameBegin = structStartLocation + STRUCT_KEYWORD.length() + 1;
      int braceBegin = shaderText.indexOf("{", nameBegin);
      int braceEnd = shaderText.indexOf("}", braceBegin);
    //  int nameEnd = shaderText.indexOf(";", nameBegin);

      String structName = shaderText.substring(nameBegin, braceBegin).trim();
      ArrayList<String> structComponents = new ArrayList<String>();        
      int componentSemicolonPosition = shaderText.indexOf(";", braceBegin);
      
      while(componentSemicolonPosition != -1 && componentSemicolonPosition < braceEnd) {
        
        int componentNameStart = componentSemicolonPosition;
        while(shaderText.charAt(componentNameStart - 1) != ' ') {
          componentNameStart--;
        }
        
      //  structComponents.add(shaderText.substring(componentNameStart, componentSemicolonPosition));
        System.out.println(shaderText.substring(componentNameStart, componentSemicolonPosition));
        componentSemicolonPosition = shaderText.indexOf(";", componentSemicolonPosition + 1);
      }

      result.put(structName, structComponents);
      structStartLocation = shaderText.indexOf(STRUCT_KEYWORD, structStartLocation + STRUCT_KEYWORD.length());
    }
    
    return result;
  }
  

  public void addAllUniforms(String shaderText) {
    
    HashMap<String, ArrayList<String>> structs = findUniformStructs(shaderText);

    final String UNIFORM_KEYWORD = "uniform";
    int uniformStartLocation = shaderText.indexOf(UNIFORM_KEYWORD);

    while (uniformStartLocation != -1) {

      int begin = uniformStartLocation + UNIFORM_KEYWORD.length() + 1;
      int end = shaderText.indexOf(";", begin);

      String uniformLine = shaderText.substring(begin, end);
      String uniformName = uniformLine.substring(uniformLine.indexOf(' ') + 1, uniformLine.length());

      addUniform(uniformName);

      uniformStartLocation = shaderText.indexOf(UNIFORM_KEYWORD, uniformStartLocation + UNIFORM_KEYWORD.length());
    }

  }

  /**
   * Adds a uniform variable for use with shader files
   *
   * @param uniform
   */
  public void addUniform(String uniform) {
    int uniformLocation = glGetUniformLocation(program, uniform);

    if (uniformLocation == 0xFFFFFF) {
      System.out.println("Error: could not find uniform " + uniform);
      new Exception().printStackTrace();
      System.exit(1);
    }

    uniforms.put(uniform, uniformLocation);
  }

  public void compileShader() {
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

  public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine) {

  }

  private void addProgram(String text, int type) {

    int shader = glCreateShader(type);

    if (shader == 0) {
      System.err.println("Shader creation failed. Could not find valid memory location in constructor.");
      System.exit(1);
    }
    glShaderSource(shader, text);

    glCompileShader(shader);

    if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
      System.err.println(glGetShaderInfoLog(shader, 1024));
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

  public static String loadShader(String fileName) {

    StringBuilder shaderSource = new StringBuilder();
    BufferedReader shaderReader = null;
    final String INCLUDE_DIRECTIVE = "#include";

    // #include "file.h"
    try {
      shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
      String line;

      while ((line = shaderReader.readLine()) != null) {

        if (line.startsWith(INCLUDE_DIRECTIVE)) {

          shaderSource.append(loadShader(line.substring(INCLUDE_DIRECTIVE.length() + 2, line.length() - 1)));
        } else {
          shaderSource.append(line).append("\n");
        }
      }

      shaderReader.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    return shaderSource.toString();
  }

}
