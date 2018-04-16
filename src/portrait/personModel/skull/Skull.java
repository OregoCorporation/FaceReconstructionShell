package portrait.personModel.skull;

import portrait.personModel.PersonPart;

import java.io.InputStream;

public final class Skull extends PersonPart {

    public Skull(final InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public final String getName() {
        return "Skull";
    }
}
