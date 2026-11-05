package colesico.zacepco.investigation.service;

import colesico.zacepco.investigation.model.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class StateEnricher {
    private final InvestigationState state;
    private final ScriptDocument script;

    public StateEnricher(InvestigationState state, ScriptDocument script) {
        this.state = state;
        this.script = script;
    }

    public InvestigationState enrich(){
        state.scriptTitle = script.metainfo.title;

        return state;
    }
}
