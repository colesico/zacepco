package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class ScriptPostprocessor implements ReadPostprocessor<ScriptDocument> {

    @Override
    public ScriptDocument process(ScriptDocument document) {
        for (var tick : document.crime.timeline.entrySet()) {
            tick.getValue().id = tick.getKey();
        }

        for (var personage : document.crime.personages.entrySet()) {
            personage.getValue().id = personage.getKey();
        }

        for (var clue : document.crime.clues.entrySet()) {
            clue.getValue().id = clue.getKey();
        }

        return document;
    }
}
