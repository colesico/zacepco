package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class InvestigationContext {

    public Long investigationId;

    public Long scriptId;

    /**
     * Investigation
     */
    public InvestigationState state;

    /**
     * Script document
     */
    public ScriptDocument script;

}
