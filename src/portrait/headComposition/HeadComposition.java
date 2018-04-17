package portrait.headComposition;

import portrait.personModel.Shader;
import portrait.personModel.exceptions.HeadComponentsAreNotBindedException;
import portrait.personModel.exceptions.NotLoadedBufferException;
import portrait.personModel.exceptions.ViolatedBindingSequenceOfPartsException;
import portrait.personModel.face.Face;
import portrait.personModel.hairStyle.HairStyle;
import portrait.personModel.skull.Skull;

import java.io.InputStream;
import java.nio.*;
import java.util.logging.Logger;

/**
 * @author Ilya Dolgushev && Igor Gulkin, 15.04.2018
 * <p>
 * Класс HeadComposition является главным классом приложения,
 * поскольку он полностью отображает 3D оболочку Вашего лица на экране.
 * При построении лица HeadComposition опирается на библиотеку OpenGL,
 * которая будет отрисовывать модель Вашей головы.
 */

public final class HeadComposition {

    private static final Logger LOG = Logger.getLogger(HeadComposition.class.getName());

    /**
     * Чтобы OpenGL отрисовало голову, необходимо создать череп, лицо, прическа соответственно:
     */

    private final Skull skull;

    private final Face face;

    private HairStyle currentHairStyle;

    private Shader shader;

    /**
     * На самом деле OpenGL отрисовывает при помощи буфферов, поэтому мы создадим наш буффер,
     * который содержит в себе вершины: x, y, z, r, g, b.
     * Таким образом, сначала у нас идут вершины черепа, затем лица.
     */

    private FloatBuffer vertexBufferObject;

    /**
     * Второй буффер хранит в себе полигоны вершин.
     * Поскольку мы будем отрисовавыть "треугольниками",
     * то мы будем хранить номера в списке трех вершин соответственно:
     */

    private IntBuffer elementBufferObject;

    /**
     * @param skullIS     InputStream .obj файла черепа человека
     * @param faceIS      InputStream .obj файла лица человека
     * @param hairStyleIS InputStream .obj файла лица человека
     *                    В конструкторе происходит загрузка вершинного и фрагментного шейдеров, а также всех трёх частей:
     */

    public HeadComposition(final InputStream skullIS, final InputStream faceIS, final InputStream hairStyleIS) {
        this.skull = new Skull(skullIS);
        this.face = new Face(faceIS);
        this.currentHairStyle = new HairStyle(hairStyleIS);
        //Неявно инциализируем буферы:
        this.vertexBufferObject = createNativeByteBuffer(face.getVertexBufferSize()
                + skull.getVertexBufferSize()).asFloatBuffer();
        this.elementBufferObject = createNativeByteBuffer(face.getElementBufferSize()
                + skull.getElementBufferSize()).asIntBuffer();
        //Загружаем шейдеры:
        this.shader = new Shader("", "");
        try {
            this.fillBuffers();
        } catch (final NotLoadedBufferException e) {
            LOG.info("Неудалось создать буфферы головы");
        }
    }

    private void fillBuffers()
            throws NotLoadedBufferException {
        for (int i = 0; i < face.getElementBufferSize(); i++) {
            if (i < face.getVertexBufferSize()) {
                vertexBufferObject.put(face.getVertexBufferObject().get(i));
            }
            elementBufferObject.put(face.getElementBufferObject().get(i));
        }
        for (int i = 0; i < skull.getElementBufferSize(); i++) {
            if (i < skull.getVertexBufferSize()){
                vertexBufferObject.put(skull.getVertexBufferObject().get(i));
            }
            elementBufferObject.put(skull.getElementBufferObject().get(i) + face.getElementBufferSize());
        }
    }

    /**
     *@author Ilya Dolgushev 17.04.18
     */

    private ByteBuffer createNativeByteBuffer(int length) {
        ByteBuffer bb = ByteBuffer.allocateDirect(length);
        bb.order(ByteOrder.nativeOrder());
        return bb;
    }

    /**
     * bindSkull() - привязывает череп к голове.
     *
     * @throws ViolatedBindingSequenceOfPartsException Поскольку мы хотим чтобы в первую очередь буфферы хранили координаты черепа,
     *                                                 Мы должны убедиться, что другие части не храняться в буфферах.
     */

    public final void bindSkull() throws ViolatedBindingSequenceOfPartsException {
        if (face.isBinded()) throw new ViolatedBindingSequenceOfPartsException();
        if (currentHairStyle.isBinded()) throw new ViolatedBindingSequenceOfPartsException();
        //make binding
        this.skull.setBinded(true);
    }

    /**
     * bindFace() - привязывает лицо к голове.
     *
     * @throws ViolatedBindingSequenceOfPartsException Дальше, во вторую очередь буфферы должны хранить координаты лица,
     *                                                 Мы должны убедиться, что череп загружен, а прическа нет.
     */

    public final void bindFace() throws ViolatedBindingSequenceOfPartsException {
        if (skull.isBinded() && !currentHairStyle.isBinded()) {
            //TODO binding

            this.face.setBinded(true);
        } else {
            throw new ViolatedBindingSequenceOfPartsException();
        }
    }

    /**
     * bindHairStyle() - привязывает прическу к голове.
     *
     * @throws ViolatedBindingSequenceOfPartsException Кладем прическу, проверив, что череп и лицо загруженно соответственно.
     */

    public final void bindHairStyle() throws ViolatedBindingSequenceOfPartsException {
        if (skull.isBinded() && face.isBinded()) {


            this.currentHairStyle.setBinded(true);
        } else {
            throw new ViolatedBindingSequenceOfPartsException();
        }

    }

    /**
     * draw() - отрисовывает голову на экране.
     *
     * @throws HeadComponentsAreNotBindedException Когда у нас все части загружены в правильном порядке и шейдеры включены,
     *                                             то можно приступить к самой вкусной части - отрисовке головы.
     *                                             Мы здесь непосредственно работаем с OpenGL:
     *                                             передаем ему буффер вершин vertexBufferObject и буффер полигонов elementBufferObject.
     */

    public final void draw() throws HeadComponentsAreNotBindedException {
        final boolean isSkullReady = skull.isBinded();
        final boolean isFaceReady = face.isBinded();
        final boolean isHairStyleReady = currentHairStyle.isBinded();
        final boolean areShadersReady = shader.isReady();
        if (isSkullReady && isFaceReady && isHairStyleReady && areShadersReady) {
            //TODO draw via OpenGL...
        } else {
            throw new HeadComponentsAreNotBindedException();
        }
    }

    /**
     * setCurrentHairStyle() - меняет прическу.
     * <p>
     * Перед сменой прически отрисовка OpenGL останавливается, и прическа меняется,
     * после чего снова включится метод отрисовки, то есть вызывается метод draw().
     */

    public final void setCurrentHairStyle(final HairStyle newHairStyle) {
        final HairStyle previousHairStyle = this.currentHairStyle;
        previousHairStyle.setBinded(false);

        //change buffers:
        newHairStyle.setBinded(true);
        this.currentHairStyle = newHairStyle;
        try {
            this.draw();
        } catch (final HeadComponentsAreNotBindedException e) {
            LOG.info("Oops! Something went wrong");
        }
    }
}
