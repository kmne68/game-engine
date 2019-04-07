#version 330

// in vec4 color; // removed in video 21
in vec2 textureCoordinate0;

uniform sampler2D sampler;

// out vec4 fragColor; // removed sometime before video 21


void main()
{
    // fragColor = color;  // changed to gl_FragColor = color sometime before video 21

    gl_FragColor = texture2D(sampler, textureCoordinate0.xy);
}