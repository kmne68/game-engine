#version 330

in vec2 textureCoordinate0;

uniform vec3 color;
uniform sampler2D sampler;

void main()
{
    vec4 textureColor = texture2D(sampler, textureCoordinate0.xy);

        // Changed right operand from 0 to vec4(0, 0, 0, 1)
        if(textureColor == vec4(0, 0, 0, 1))
            gl_FragColor = vec4(color, 1);
        else
            gl_FragColor = textureColor * vec4(color, 1);

}
