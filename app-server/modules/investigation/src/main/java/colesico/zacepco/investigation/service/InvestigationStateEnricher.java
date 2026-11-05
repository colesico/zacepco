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
        state.scriptTitle = script.metainfo.title;

        for (var locationSpec : script.crime.scene.locations) {
            var locationState = state.locations.get(locationSpec.id.toString());
            locationState.name = locationSpec.name;
            locationState.description = locationSpec.description;
            locationState.ref = locationSpec.ref;
        }

        for (var personageSpec : script.crime.personages) {
            var personageState = state.personages.get(personageSpec.id.toString());
            personageState.name = personageSpec.name;
            personageState.description = personageSpec.description;
            personageState.ref = personageSpec.ref;
        }

        return state;
    }
}
