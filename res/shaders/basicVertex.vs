#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoordinate;    // added in video #21

// out vec4 color;  // deleted in video 21
out vec2 textureCoordinate0;

// uniform float uniformFloat;
uniform mat4 transform;

void main()
{
    // color = vec4(clamp(position, 0.0, 1.0), 1.0);
    
    //color = vec4(clamp(position, 0.0, uniformFloat), 1.0);
    // gl_Position = vec4(0.5 * position, 1.0);
    
    gl_Position = transform * vec4(position, 1.0);
    textureCoordinate0 = textureCoordinate;
}