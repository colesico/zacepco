package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public class ClueState {
    public ClueSpec.Id id;

    public boolean discovered = false;

    public ClueState() {
    }

    public ClueState(ClueSpec.Id id) {
        this.id = id;
    }
}
