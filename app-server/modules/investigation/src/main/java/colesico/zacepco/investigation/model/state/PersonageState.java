package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.PersonageSpec;

public class PersonageState {

    public PersonageSpec.Id id;

    /**
     * Current uncover or not.
     */
    public boolean hidden = false;

    /**
     * Whether the personage can still be interrogated.
     * True if no interrogation has been performed for the personage yet,
     * or if an interrogation has been performed and unexposed clues still remain.
     * Set to {@code false} once all clues for the personage have been exposed.
     */
    public boolean interrogationAvailable = true;

    public PersonageState() {
    }

    public PersonageState(PersonageSpec.Id id) {
        this.id = id;
    }
}
