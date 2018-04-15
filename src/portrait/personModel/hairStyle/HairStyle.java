package portrait.personModel.hairStyle;

import portrait.personModel.PersonPart;

public final class HairStyle extends PersonPart {

    public HairStyle(final String path) {
        super(path);
    }

    @Override
    public final String getName() {
        return "HairStyle";
    }
}
