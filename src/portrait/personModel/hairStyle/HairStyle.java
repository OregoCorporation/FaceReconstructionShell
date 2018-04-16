package portrait.personModel.hairStyle;

import portrait.personModel.PersonPart;

import java.io.InputStream;

public final class HairStyle extends PersonPart {

    public HairStyle(final InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public final String getName() {
        return "HairStyle";
    }
}
