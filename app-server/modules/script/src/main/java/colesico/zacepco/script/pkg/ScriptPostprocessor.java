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
            for (var personageEvent : personage.getValue().log.entrySet()) {
                personageEvent.getValue().tick = personageEvent.getKey();
            }
        }

        for (var clue : document.crime.clues.entrySet()) {
            clue.getValue().id = clue.getKey();
        }

        for (var location : document.crime.scene.locations.entrySet()) {
            location.getValue().id = location.getKey();
            for (var locationEvent : location.getValue().log.entrySet()) {
                locationEvent.getValue().tick = locationEvent.getKey();
            }
        }

        for (var way : document.crime.scene.ways.entrySet()) {
            way.getValue().id = way.getKey();
        }

        for (var focusedClue : document.investigation.search.clues.entrySet()) {
            focusedClue.getValue().clue = focusedClue.getKey();
        }

        for (var focusedClue : document.investigation.interrogation.clues.entrySet()) {
            focusedClue.getValue().clue = focusedClue.getKey();
        }

        for (var focusedClue : document.investigation.expertise.clues.entrySet()) {
            focusedClue.getValue().clue = focusedClue.getKey();
        }

        for (var detective : document.investigation.detectives.entrySet()) {
            detective.getValue().id = detective.getKey();
        }

        return document;
    }
}
