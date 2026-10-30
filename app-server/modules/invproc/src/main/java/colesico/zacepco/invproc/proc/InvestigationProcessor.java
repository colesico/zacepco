package colesico.zacepco.invproc.proc;

import colesico.zacepco.invproc.model.InvestigationState;

public class InvestigationProcessor {
    private final InvestigationState state;

    public InvestigationProcessor(InvestigationState state) {
        this.state = state;
    }

    public InvestigationProcessor(InvestigationStateBuilder stateBuilder){
        this.state = stateBuilder.build();
    }

    public InvestigationState state() {
        return state;
    }


}
