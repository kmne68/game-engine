#version 330

layout (location = 0) in vec3 position;

out vec4 color;

uniform float uniformFloat;

void main()
{
    // color = vec4(clamp(position, 0.0, 1.0), 1.0);
    color = vec4(clamp(position, 0.0, uniformFloat), 1.0);
    gl_Position = vec4(0.5 * position, 1.0);
}