package colesico.zacepco.investigation.service;

import colesico.zacepco.investigation.model.InvestigationState;
import colesico.zacepco.investigation.model.InvestigationStateBuilder;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class InvestigationManager {

    public final ScriptDocument script;

    public final InvestigationState state;

    public LocationSearchManager locationSearchManager;

    public InvestigationManager(ScriptDocument script, InvestigationState state) {
        if (!script.metainfo.id.equals(state.scriptId)) {
            throw new IllegalStateException("State script id mismatch");
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
