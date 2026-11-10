package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class ScriptPostprocessor implements ReadPostprocessor<ScriptDocument> {

    @Override
    public ScriptDocument process(ScriptDocument document) {
        for (var tick : document.crime.timeline.entrySet()) {
            tick.getValue().id = tick.getKey();
        }
        return document;
    }
}
