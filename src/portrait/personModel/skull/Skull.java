package portrait.personModel.skull;

import portrait.personModel.PersonPart;

public final class Skull extends PersonPart {

    public Skull(String path) {
        super(path);
    }

    @Override
    public final String getName() {
        return "Skull";
    }
}
