package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.docket.srv.model.ScriptEntry;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;
import colesico.zacepco.script.model.scriptdoc.ScriptMetainfoSpec;

import java.util.ArrayList;
import java.util.List;

public class ScriptOverview {
    public ScriptEntry entry;
    public ScriptDocument document;

    public ScriptOverview() {
    }

    public ScriptOverview(ScriptEntry entry, ScriptDocument document) {
        this.entry = entry;
        this.document = document;
    }
}
