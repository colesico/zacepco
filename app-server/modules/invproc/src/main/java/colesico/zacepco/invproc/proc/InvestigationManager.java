package colesico.zacepco.invproc.proc;

import colesico.zacepco.invproc.model.InvestigationState;
import colesico.zacepco.scriptpack.model.script.ScriptDocument;

public class InvestigationManager {

    public final ScriptDocument script;

    public final InvestigationState state;

    public InvestigationManager(ScriptDocument script, InvestigationState state) {
        this.script = script;
        this.state = state;
    }

    public InvestigationManager(InvestigationStateBuilder stateBuilder){
        this.script = stateBuilder.script();
        this.state = stateBuilder.build();
    }




}
