package portrait.personModel;

import portrait.materials.Materials;
import portrait.personModel.exceptions.NotLoadedBufferException;
import portrait.personModel.exceptions.NotLoadedMaterialsException;

import java.io.InputStream;
import java.nio.Buffer;

/**
 * Класс PersonPart
 */

public abstract class PersonPart {

    private boolean isBinded;

    private ModelLoader modelLoader;

    /**
     * @param inputStream
     *        InputStream .obj файла
     *
     * В конструкторе происходит инициализация загрузчика 3D модели ModelLoader,
     * затем сразу же вызывается modelLoader.load(), что говорит нам о том,
     * что началась загрузка всех нужных нам файлов.
     */

    public PersonPart(final InputStream inputStream){
        final ModelLoader modelLoader = new ModelLoader(inputStream);
        modelLoader.load();
        this.modelLoader = modelLoader;
        this.isBinded = false;
    }

    /**
     * @return возвращает float буфер вершин;
     * @throws NotLoadedBufferException
     */

    public final Buffer getVertexBufferObject() throws NotLoadedBufferException{
        final Buffer buffer = modelLoader.getVertexBufferObject();
        if (buffer != null){
            return buffer;
        } else {
            throw new NotLoadedBufferException("Not loaded" + getName() + "vertex buffer object");
        }
    }


    /**
     * @return возвращает имя части головы
     */

   public abstract String getName();

    /**
     * @return возвращает float буфер вершин;
     * @throws NotLoadedBufferException
     */

    public final Buffer getElementBufferObject() throws NotLoadedBufferException{
        final Buffer buffer = modelLoader.getElementBufferObject();
        if (buffer != null){
            return buffer;
        } else {
            throw new NotLoadedBufferException("Not loaded" + getName() + "element buffer object");
        }
    }

    /**
     * @return возвращает материалы для 3D объекта
     * @throws NotLoadedMaterialsException
     */

    public final Materials getMaterials() throws NotLoadedMaterialsException{
        final Materials materials = modelLoader.getMaterials();
        if (materials != null){
            return materials;
        } else {
            throw new NotLoadedMaterialsException("Not loaded" + getName() + "materials");
        }
    }


    /**
     * @return указывает готовность буферов
     */

    public final boolean isBinded(){
        return isBinded;
    }

    /**
     * @return устанавливает готовность буферов
     */

    public final void setBinded(final boolean binded) {
        this.isBinded = binded;
    }
}
