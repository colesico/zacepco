package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.state.InvestigationState;

import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class InvestigationStateEnricher {
    private final InvestigationState state;
    private final ScriptDocument script;

    public InvestigationStateEnricher(InvestigationState state, ScriptDocument script) {
        this.state = state;
        this.script = script;
    }

    public InvestigationState enrich() {

        enrichScene();
        enrichPersonages();

        return state;
    }

    private void enrichPersonages() {

    }

    private void enrichScene() {

    }
}
