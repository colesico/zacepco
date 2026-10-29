package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.Clue;
import colesico.zacepco.scriptpack.model.script.Personage;

import java.util.ArrayList;
import java.util.List;

public class PersonageState {

    public Personage.Id id;

    /**
     * Personage discover state.
     */
    public Boolean hidden = false;


    public List<Clue.Id> discoveredClues = new ArrayList<>();

    public Personage.Id getId() {
        return id;
    }

    public void setId(Personage.Id id) {
        this.id = id;
    }

    public List<Clue.Id> getDiscoveredClues() {
        return discoveredClues;
    }

    public void setDiscoveredClues(List<Clue.Id> discoveredClues) {
        this.discoveredClues = discoveredClues;
    }
}
