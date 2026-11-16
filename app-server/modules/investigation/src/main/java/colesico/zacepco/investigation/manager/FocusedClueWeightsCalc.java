package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.model.data.FocusedClueWeights;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

public class FocusedClueWeightsCalc {

    public static FocusedClueWeights calculate(FocusedClueSpec focusedClue, DetectiveFocus detectiveFocus) {
        // EQ: exact ID matches between detective focus and table focus
        var eqIds = new HashSet<>(detectiveFocus.ids());
        eqIds.retainAll(focusedClue.focus);

        // Remaining IDs after EQ is excluded from both sides
        var detectiveIds = new HashSet<>(detectiveFocus.ids());
        detectiveIds.removeAll(eqIds);

        var tableIds = new HashSet<>(focusedClue.focus);
        tableIds.removeAll(eqIds);

        // EQT: detective type matches against types of the remaining table IDs
        var tableTypes = EnumSet.noneOf(TId.Type.class);
        for (TId id : tableIds) {
            tableTypes.add(id.type());
        }

        var eqtTypes = EnumSet.noneOf(TId.Type.class);
        eqtTypes.addAll(detectiveFocus.types());
        eqtTypes.retainAll(tableTypes);

        // NE: everything left unmatched on both sides
        long eq  = eqIds.size();
        long eqt = eqtTypes.size();
        long ne  = detectiveIds.size()
                + (detectiveFocus.types().size() - eqt)
                + (tableIds.size() - eqt);

        return new FocusedClueWeights(focusedClue, eq, eqt, ne);
    }

    public static List<FocusedClueWeights> calculate(List<FocusedClueSpec> focusedClues, DetectiveFocus detectiveFocus) {
        return focusedClues.stream()
                .map(focusedClue -> calculate(focusedClue, detectiveFocus))
                .toList();
    }
}