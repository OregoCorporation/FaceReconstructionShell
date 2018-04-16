package shaderProgramTest;

import org.junit.Test;
import portrait.personModel.Shader;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public final class ShaderTest {

    private static final Logger LOG = Logger.getLogger(ShaderTest.class.getName());

    @Test
    public final void loadingTest() {
        final String vertexShaderPath = "./test/shaderProgramTest/shaderFiles/vertex.shader";
        final String fragmentShaderPath = "./test/shaderProgramTest/shaderFiles/vertex.shader";
        final Shader shader = new Shader(vertexShaderPath, fragmentShaderPath);
        LOG.info(shader.toString());
        assertTrue(shader.isReady());
    }
}
