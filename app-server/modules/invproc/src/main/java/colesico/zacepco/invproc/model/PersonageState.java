package colesico.zacepco.invproc.model;

import colesico.zacepco.scriptpack.model.script.ClueSpec;
import colesico.zacepco.scriptpack.model.script.PersonageSpec;

import java.util.ArrayList;
import java.util.List;

public class PersonageState {

    public PersonageSpec.Id id;

    /**
     * Personage uncover  state.
     */
    public boolean hidden = false;

    public List<ClueSpec.Id> exposedClues = new ArrayList<>();

    public PersonageSpec.Id getId() {
        return id;
    }

    public void setId(PersonageSpec.Id id) {
        this.id = id;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<ClueSpec.Id> getExposedClues() {
        return exposedClues;
    }

    public void setExposedClues(List<ClueSpec.Id> exposedClues) {
        this.exposedClues = exposedClues;
    }
}
