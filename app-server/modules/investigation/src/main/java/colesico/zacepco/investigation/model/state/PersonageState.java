package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.PersonageSpec;

import java.util.ArrayList;
import java.util.List;

public class PersonageState {

    public PersonageSpec.Id id;

    /**
     * Current uncover or not.
     */
    public boolean hidden = false;

    /**
     * Current exposed clues
     */
    public List<ClueSpec.Id> exposedClues = new ArrayList<>();

    public PersonageState() {
    }

    public PersonageState(PersonageSpec.Id id) {
        this.id = id;
    }
}
