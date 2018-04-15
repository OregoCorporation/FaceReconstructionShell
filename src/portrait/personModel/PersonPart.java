package portrait.personModel;

import portrait.materials.Materials;
import portrait.personModel.exceptions.NotLoadedBufferException;
import portrait.personModel.exceptions.NotLoadedMaterialsException;

import java.nio.Buffer;

/**
 * Класс PersonPart
 */

public abstract class PersonPart {

    private boolean isBinded;

    private ModelLoader modelLoader;

    /**
     * @param path
     *        указывает путь к .obj файлу
     *
     * В конструкторе происходит инициализация загрузчика 3D модели ModelLoader,
     * затем сразу же вызывается modelLoader.load(), что говорит нам о том,
     * что началась загрузка всех нужных нам файлов
     */

    public PersonPart(final String path){
        final ModelLoader modelLoader = new ModelLoader(path);
        modelLoader.load();
        this.modelLoader = modelLoader;
        this.isBinded = false;
    }

    public final Buffer getVertexBufferObject() throws NotLoadedBufferException{
        final Buffer buffer = modelLoader.getVertexBufferObject();
        if (buffer != null){
            return buffer;
        } else {
            throw new NotLoadedBufferException("Not loaded" + getName() + "vertex buffer object");
        }
    }

   public abstract String getName();

    public final Buffer getElementBufferObject() throws NotLoadedBufferException{
        final Buffer buffer = modelLoader.getElementBufferObject();
        if (buffer != null){
            return buffer;
        } else {
            throw new NotLoadedBufferException("Not loaded" + getName() + "element buffer object");
        }
    }

    public final Materials getMaterials() throws NotLoadedMaterialsException{
        final Materials materials = modelLoader.getMaterials();
        if (materials != null){
            return materials;
        } else {
            throw new NotLoadedMaterialsException("Not loaded" + getName() + "materials");
        }
    }

    public final boolean isBinded(){
        return isBinded;
    }

    public final void setBinded(final boolean binded) {
        this.isBinded = binded;
    }
}
