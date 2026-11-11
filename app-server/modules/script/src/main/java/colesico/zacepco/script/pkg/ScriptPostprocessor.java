package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
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

        for (var location : document.crime.scene.locations.entrySet()) {
            location.getValue().id = location.getKey();
        }

        for (var way : document.crime.scene.ways.entrySet()) {
            way.getValue().id = way.getKey();
        }

        for (var locationSearch : document.investigation.search.locations.entrySet()) {
            locationSearch.getValue().location = locationSearch.getKey();
            for (var focusedClue : locationSearch.getValue().clues.entrySet()) {
                focusedClue.getValue().clue = focusedClue.getKey();
            }
        }

        for (var personageInterog : document.investigation.interrogation.personages.entrySet()) {
            personageInterog.getValue().personage = personageInterog.getKey();
            for (var focusedClue : personageInterog.getValue().clues.entrySet()) {
                focusedClue.getValue().clue = focusedClue.getKey();
            }
        }

        for (var focusedClue : document.investigation.expertise.clues.entrySet()) {
            focusedClue.getValue().clue = focusedClue.getKey();
        }

        for (var personageLog : document.answers.personageLogs.entrySet()) {
            personageLog.getValue().personage = personageLog.getKey();
            for (var personageEvent : personageLog.getValue().events.entrySet()) {
                personageEvent.getValue().tick = personageEvent.getKey();
            }
        }

        return document;
    }
}
