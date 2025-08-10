#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords) * v_color;
    color.rgb *= 0.5; // Reduce brightness (darken)
    color.rgb += vec3(0.0, 0.0, 0.05); // Add subtle blue tint
    gl_FragColor = color;
}
