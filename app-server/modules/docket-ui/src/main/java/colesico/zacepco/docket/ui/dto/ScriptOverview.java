package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.docket.srv.model.ScriptEntry;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class ScriptOverview {
    public ScriptEntry entry;
    public ScriptDocument script;

    public ScriptOverview() {
    }

    public ScriptOverview(ScriptEntry entry, ScriptDocument script) {
        this.entry = entry;
        this.script = script;
    }
}
