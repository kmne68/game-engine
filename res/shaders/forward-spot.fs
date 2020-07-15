#version 120
#include "lighting.glh"

varying vec2 textureCoordinate0;
varying vec3 normal0;
varying vec3 worldPosition0;

uniform sampler2D diffuse;
uniform SpotLight spotLight;

void main()
{	
    gl_FragColor = texture2D(diffuse, textureCoordinate0.xy) * calculateSpotLight(spotLight, normalize(normal0), worldPosition0);
}
