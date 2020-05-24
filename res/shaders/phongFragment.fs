#version 330

const int MAX_POINT_LIGHTS = 4;

in vec2 textureCoordinate0;
in vec3 normal0;
in vec3 worldPosition0;

out vec4 fragColor;


struct BaseLight
{
    vec3 color;
    float intensity;
};

struct DirectionalLight
{
    BaseLight base;
    vec3 direction;
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

uniform vec3 baseColor;
uniform vec3 eyePosition;
uniform vec3 ambientLight;
uniform sampler2D sampler;

uniform float specularIntensity;
uniform float specularPower;

uniform DirectionalLight directionalLight;
uniform PointLight pointLights[MAX_POINT_LIGHTS];


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


vec4 calculateDirectionalLight(DirectionalLight directionalLight, vec3 normal)
{
    return calculateLight(directionalLight.base, -directionalLight.direction, normal);
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
    vec4 totalLight = vec4(ambientLight, 1);
    vec4 color = vec4(baseColor, 1);
    vec4 textureColor = texture(sampler, textureCoordinate0.xy);
 
    if( textureColor != vec4(0, 0, 0, 0 ) )
        color *= textureColor;

    vec3 normal = normalize(normal0);

    totalLight += calculateDirectionalLight(directionalLight, normal);

    for(int i = 0; i < MAX_POINT_LIGHTS; i++)
        if(pointLights[i].base.intensity > 0)
            totalLight += calculatePointLight(pointLights[i], normal);
			
    fragColor = color * totalLight;

}
