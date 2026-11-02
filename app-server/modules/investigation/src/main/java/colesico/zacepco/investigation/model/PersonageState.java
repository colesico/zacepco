package colesico.zacepco.investigation.model;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

import java.util.ArrayList;
import java.util.List;

public class PersonageState {

    public final String id;

    /**
     * Personage uncover  state.
     */
    public boolean hidden = false;

    public List<ClueSpec.Id> exposedClues = new ArrayList<>();

    public PersonageState(String id) {
        this.id = id;
    }
}
