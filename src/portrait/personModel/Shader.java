package portrait.personModel;

public final class Shader {

    private boolean isReady;

    private int program;

    public Shader(final String vertexShaderPath, final String fragmentShaderPath) {
        this.isReady = true;
        //catch...
        // else false...
    }

    public final boolean isReady() {
        return isReady;
    }

    public final void use(){
        //включаем шейдеры в OpenGL
    }
}
