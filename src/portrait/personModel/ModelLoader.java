package portrait.personModel;

import portrait.materials.Materials;

import java.nio.Buffer;

final class ModelLoader {

    private final String path;

    private boolean isLoaded;

    private Buffer vertexBufferObject;

    private Buffer elementBufferObject;

    private Materials materials;

    ModelLoader(final String path){
        this.path = path;
        this.isLoaded = false;
    }

    final void load(){
        if (!isLoaded){
            //loading...
            //...
            //...complete
            this.isLoaded = true;
        }
    }

    final Buffer getVertexBufferObject() {
        return isLoaded ? vertexBufferObject : null;
    }

    final Buffer getElementBufferObject() {
        return isLoaded ? elementBufferObject : null;
    }

    final Materials getMaterials(){
        return isLoaded ? materials : null;
    }
}