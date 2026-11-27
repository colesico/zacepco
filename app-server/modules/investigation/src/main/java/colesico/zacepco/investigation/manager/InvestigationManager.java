package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;
import colesico.zacepco.script.model.scriptdoc.PersonageSpec;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;
import colesico.zacepco.script.model.scriptdoc.TId;

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

    public SearchManager searchManager() {
        return new SearchManager(this);
    }

    public InterrogationManager interrogationManager() {
        return new InterrogationManager(this);
    }

    public InvestigationState state() {
        return state;
    }

    public ScriptDocument script() {
        return script;
    }
}
