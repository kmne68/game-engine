#version 120

varying vec2 textureCoordinate0;
varying vec3 normal0;
varying vec3 worldPosition0;

struct BaseLight
{
    vec3 color;
    float intensity;
};

struct Attenuation
{
    float constant;
    float linear;
    float exponent;
};

struct PointLight
{
    BaseLight base;
    Attenuation attenuation;
    vec3 position;
    float range;
};

uniform vec3 eyePosition;
uniform sampler2D diffuse;

uniform float specularIntensity;
uniform float specularPower;

uniform PointLight pointLight;

vec4 calculateLight(BaseLight base, vec3 direction, vec3 normal)
{
    float diffuseFactor = dot(normal, -direction);

    vec4 diffuseColor = vec4(0, 0, 0, 0);
    vec4 specularColor = vec4(0, 0, 0, 0);

    if(diffuseFactor > 0)
    {
        diffuseColor = vec4(base.color, 1.0) * base.intensity * diffuseFactor;

        vec3 directionToEye = normalize(eyePosition - worldPosition0);
        vec3 reflectionDirection = normalize(reflect( direction, normal ) );

        float specularFactor = dot(directionToEye, reflectionDirection);
        specularFactor = pow(specularFactor, specularPower);

        if(specularFactor > 0) {

            specularColor = vec4(base.color, 1.0) * specularIntensity * specularFactor;
        }
            
    }

    return diffuseColor + specularColor;
}


vec4 calculatePointLight(PointLight pointLight, vec3 normal)
{
    vec3 lightDirection = worldPosition0 - pointLight.position;
    float distanceToPoint = length(lightDirection);

    if(distanceToPoint > pointLight.range)
        return vec4(0, 0, 0, 0);

    lightDirection = normalize(lightDirection);

    vec4 color = calculateLight(pointLight.base, lightDirection, normal);

    float attenuation = pointLight.attenuation.constant + 
                        pointLight.attenuation.linear * distanceToPoint +
                        pointLight.attenuation.exponent * distanceToPoint * distanceToPoint +
                        0.0001;     // 0.0001 is added to prevent division by zero (the if conditional is unreliable apparently) 

    return color / attenuation;
}


void main()
{
			
    gl_FragColor = texture2D(diffuse, textureCoordinate0.xy) * calculatePointLight(pointLight, normalize(normal0));

}
