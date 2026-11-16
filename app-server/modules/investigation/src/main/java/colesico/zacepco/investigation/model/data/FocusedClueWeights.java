package colesico.zacepco.investigation.model.data;

import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;

public record FocusedClueWeights(
        FocusedClueSpec clue,
        double eq,
        double eqt,
        double ne
) {
}

