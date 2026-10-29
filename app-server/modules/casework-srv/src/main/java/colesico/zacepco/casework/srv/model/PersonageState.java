package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.Clue;
import colesico.zacepco.scriptpack.model.script.Personage;

import java.util.ArrayList;
import java.util.List;

public class PersonageState {

    public Personage.Id id;

    /**
     * Personage uncover  state.
     */
    public Boolean hidden = false;


    public List<Clue.Id> uncoveredClues = new ArrayList<>();

    public Personage.Id getId() {
        return id;
    }

    public void setId(Personage.Id id) {
        this.id = id;
    }

    public List<Clue.Id> getUncoveredClues() {
        return uncoveredClues;
    }

    public void setUncoveredClues(List<Clue.Id> uncoveredClues) {
        this.uncoveredClues = uncoveredClues;
    }
}
