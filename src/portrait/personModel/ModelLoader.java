package portrait.personModel;
import portrait.materials.Materials;

import java.io.InputStream;
import java.nio.Buffer;

final class ModelLoader {

    private final InputStream is;

    private boolean isLoaded;

    private Buffer vertexBufferObject;

    private Buffer elementBufferObject;

    private Materials materials;


    ModelLoader(final InputStream is){
        this.is = is;
        this.isLoaded = false;
    }

    final void load() {
        if (!isLoaded) {

            this.isLoaded = true;
        }
    }

    final Buffer getVertexBufferObject() {
        return isLoaded ? vertexBufferObject : null;
    }

    final Buffer getElementBufferObject() {
        return isLoaded ? elementBufferObject : null;
    }

    final Materials getMaterials() {
        return isLoaded ? materials : null;
    }

}