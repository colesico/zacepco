package colesico.zacepco.investigation.service;

import colesico.zacepco.investigation.model.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

public class InvestigationStateEnricher {
    private final InvestigationState state;
    private final ScriptDocument script;

    public InvestigationStateEnricher(InvestigationState state, ScriptDocument script) {
        this.state = state;
        this.script = script;
    }

    public InvestigationState enrich() {
        state.script.title = script.metainfo.title;
        state.script.annotation = script.metainfo.annotation;

        enrichScene();
        enrichPersonages();

        return state;
    }

    private void enrichPersonages() {
        for (var personageSpec : script.crime.personages) {
            var personageState = state.personages.get(personageSpec.id.toString());
            personageState.name = personageSpec.name;
            personageState.description = personageSpec.description;
            personageState.ref = personageSpec.ref;
        }
    }

    private void enrichScene() {
        state.scene.title = script.crime.scene.title;
        state.scene.cols = script.crime.scene.size.cols;
        state.scene.rows = script.crime.scene.size.rows;

        for (var locationSpec : script.crime.scene.locations) {
            var locationState = state.scene.locations.get(locationSpec.id.toString());
            locationState.name = locationSpec.name;
            locationState.description = locationSpec.description;
            locationState.ref = locationSpec.ref;
        }
    }
}
