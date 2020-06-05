#version 120

attribute vec3 position;
attribute vec2 textureCoordinate;
attribute vec3 normal;

varying vec2 textureCoordinate0;
varying vec3 normal0;
varying vec3 worldPosition0;


uniform mat4 model;
uniform mat4 MVP;

void main()
{    
    gl_Position = MVP * vec4(position, 1.0);
    textureCoordinate0 = textureCoordinate;
    normal0 = (model * vec4(normal, 0.0) ).xyz;
    worldPosition0 = (model * vec4(position, 1.0 ) ).xyz;

}