package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class InvestigationManager {

    public final ScriptDocument script;

    public final InvestigationState state;

    public InvestigationManager(ScriptDocument script, InvestigationState state) {
        if (!(script.metainfo.uid.equals(state.script.uid)
                && script.metainfo.version.equals(state.script.version))) {
            throw new IllegalStateException("Script id/version mismatch for investigation state");
        }
        this.script = script;
        this.state = state;
    }

    public InvestigationManager(InvestigationStateBuilder stateBuilder) {
        this.script = stateBuilder.script();
        this.state = stateBuilder.build();

    }

    public LocationSearchManager locationSearchManager(LocationSpec.Id locationId) {
        return new LocationSearchManager(this, locationId);
    }

    public InvestigationState state() {
        return state;
    }

    public ScriptDocument script() {
        return script;
    }
}
