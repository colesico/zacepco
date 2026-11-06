package colesico.zacepco.investigation.model;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

import java.util.ArrayList;
import java.util.List;

public class PersonageState {

    public String id;

    @Stateless
    public String name;

    @Stateless
    public String description;

    @Stateless
    public String ref;

    /**
     * Personage uncover  state.
     */
    public boolean hidden = false;

    public List<String> exposedClues = new ArrayList<>();

    public PersonageState() {
    }

    public PersonageState(String id) {
        this.id = id;
    }
}
