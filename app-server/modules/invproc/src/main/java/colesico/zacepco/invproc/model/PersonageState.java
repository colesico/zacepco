package colesico.zacepco.invproc.model;

import colesico.zacepco.scriptpack.model.script.ClueSpec;
import colesico.zacepco.scriptpack.model.script.PersonageSpec;

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
