package portrait.headComposition;

import portrait.personModel.exceptions.HeadComponentsAreNotBindedException;
import portrait.personModel.exceptions.ViolatedBindingSequenceOfPartsException;
import portrait.personModel.face.Face;
import portrait.personModel.hairStyle.HairStyle;
import portrait.personModel.skull.Skull;

import java.nio.Buffer;


/**
 * @author Ilya Dolgushev && Igor Gulkin
 * @
 * Класс HeadComposition содержит в себе @param skull
 */


public final class HeadComposition {

    /**
     *
     */
    private final Skull skull;

    private final Face face;

    private HairStyle currentHairStyle;

    private Buffer vertexBufferObject;

    private Buffer elementBufferObject;


    /**
     * @param skullPath
     *        указывает путь к .obj файлу черепа человека
     *
     */

    public HeadComposition(final String skullPath, final String facePath, final String hairStylePath) {
        this.skull = new Skull(skullPath);
        this.face = new Face(facePath);
        this.currentHairStyle = new HairStyle(hairStylePath);
    }

    public final void bindSkull() throws ViolatedBindingSequenceOfPartsException {
        if (face.isBinded()) throw new ViolatedBindingSequenceOfPartsException();
        if (currentHairStyle.isBinded()) throw new ViolatedBindingSequenceOfPartsException();
        //make binding
    }

    public final void bindFace() throws ViolatedBindingSequenceOfPartsException {
        if (skull.isBinded()) {
            //make binding
        } else {
            throw new ViolatedBindingSequenceOfPartsException();
        }
    }

    public final void bindHairStyle() {

    }

    public final void draw() throws HeadComponentsAreNotBindedException {
        final boolean isSkullReady = skull.isBinded();
        final boolean isFaceReady = face.isBinded();
        final boolean isHairStyleReady = currentHairStyle.isBinded();
        if (isSkullReady && isFaceReady && isHairStyleReady) {
            //draw via OpenGL...
        } else {
            throw new HeadComponentsAreNotBindedException();
        }
    }

    public final void setCurrentHairStyle(final HairStyle newHairStyle) {
        final HairStyle previousHairStyle = this.currentHairStyle;
        previousHairStyle.setBinded(false);
        newHairStyle.setBinded(true);
        this.currentHairStyle = newHairStyle;
    }
}
