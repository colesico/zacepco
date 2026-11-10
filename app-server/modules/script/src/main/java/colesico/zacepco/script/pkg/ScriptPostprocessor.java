package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class ScriptPostprocessor implements ReadPostprocessor<ScriptDocument> {

    @Override
    public ScriptDocument process(ScriptDocument document) {
        return document;
    }
}
