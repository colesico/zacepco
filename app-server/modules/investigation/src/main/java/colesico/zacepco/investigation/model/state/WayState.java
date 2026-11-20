package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.WaySpec;

public class WayState {
    public WaySpec.Id id;

    public boolean hidden = false;

    public WayState() {
    }

    public WayState(WaySpec.Id id) {
        this.id = id;
    }
}
