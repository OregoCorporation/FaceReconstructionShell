package portrait.personModel.face;

import portrait.personModel.PersonPart;

public final class Face extends PersonPart {

    public Face(String path) {
        super(path);
    }

    @Override
    public final String getName() {
        return "Face";
    }
}
