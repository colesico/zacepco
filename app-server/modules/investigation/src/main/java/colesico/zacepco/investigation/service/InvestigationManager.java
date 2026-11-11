package colesico.zacepco.investigation.service;

import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class InvestigationManager {

    public final ScriptDocument script;

    public final InvestigationState state;

    public LocationSearchManager locationSearchManager;

    public InvestigationManager(ScriptDocument script, InvestigationState state) {
        if (!(script.script.id.equals(state.script.id) && script.script.version.equals(state.script.version))) {
            throw new IllegalStateException("State script mismatch");
        }
        this.script = script;
        this.state = state;
        locationSearchManager = new LocationSearchManager(this);
    }

    public InvestigationManager(InvestigationStateBuilder stateBuilder) {
        this.script = stateBuilder.script();
        this.state = stateBuilder.build();
        locationSearchManager = new LocationSearchManager(this);
    }


}
