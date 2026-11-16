package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.dto.DetectiveFocus;
import colesico.zacepco.investigation.model.dto.FocusedClueWeights;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

public class FocusedClueUtil {

    public static FocusedClueWeights calculateWeights(FocusedClueSpec spec, DetectiveFocus detectiveFocus) {
        var detectiveIds = detectiveFocus.ids();
        var detectiveTypes = detectiveFocus.types();
        var focus = spec.focus;

        // EQ: exact ID matches between detective focus and table focus
        var matchedIds = new HashSet<TId>(detectiveIds);
        matchedIds.retainAll(focus);

        // Table IDs left after EQ is excluded from both sides
        var tableIds = new HashSet<>(focus);
        tableIds.removeAll(matchedIds);

        // Types of the remaining table IDs
        var tableTypes = EnumSet.noneOf(TId.Type.class);
        tableIds.forEach(id -> tableTypes.add(id.type()));

        // EQT: detective types matching types of the remaining table IDs
        var matchedTypes = EnumSet.noneOf(TId.Type.class);
        matchedTypes.addAll(detectiveTypes);
        matchedTypes.retainAll(tableTypes);

        // Types of the remaining table IDs that were NOT matched by detective types
        var unmatchedTypes = tableTypes.clone();
        unmatchedTypes.removeAll(matchedTypes);

        // NE: unmatched detective IDs + unmatched detective types + unmatched table IDs
        long eq = matchedIds.size();
        long eqt = matchedTypes.size();
        long ne = (detectiveIds.size() - eq)
                + (detectiveTypes.size() - eqt)
                + (tableIds.size() - eqt);

        return new FocusedClueWeights(spec, eq, eqt, ne, matchedIds, matchedTypes, unmatchedTypes);
    }

    public static List<FocusedClueWeights> calculateWeights(List<FocusedClueSpec> focusedClues, DetectiveFocus detectiveFocus) {
        return focusedClues.stream()
                .map(focusedClue -> calculateWeights(focusedClue, detectiveFocus))
                .toList();
    }
}