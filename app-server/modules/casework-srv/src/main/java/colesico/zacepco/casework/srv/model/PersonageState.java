package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.ClueSpec;
import colesico.zacepco.scriptpack.model.script.PersonageSpec;

import java.util.ArrayList;
import java.util.List;

public class PersonageState {

    public PersonageSpec.Id id;

    /**
     * Personage uncover  state.
     */
    public Boolean hidden = false;


    public List<ClueSpec.Id> uncoveredClues = new ArrayList<>();

    public PersonageSpec.Id getId() {
        return id;
    }

    public void setId(PersonageSpec.Id id) {
        this.id = id;
    }

    public List<ClueSpec.Id> getUncoveredClues() {
        return uncoveredClues;
    }

    public void setUncoveredClues(List<ClueSpec.Id> uncoveredClues) {
        this.uncoveredClues = uncoveredClues;
    }
}
