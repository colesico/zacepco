package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.dto.FocusedClueMatch;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.*;

public class InvestigationUtil {

    public static boolean checkD12(int d12Value) {
        return d12Value > 0 && d12Value <= 12;
    }

    public Set<TId.Type> extractIdTypes(Collection<TId> tids) {
        var types = EnumSet.noneOf(TId.Type.class);
        tids.stream().filter(Objects::nonNull).forEach(id -> types.add(id.type()));
        return types;
    }

    public void checkClueExposed(DetectiveFocus focus, InvestigationState state) {

        Set<TId> exposedIds = state.findExposedEntities();
        Set<TId.Type> exposedTypes = extractIdTypes(exposedIds);

        boolean covered = exposedIds.containsAll(focus.ids())
                && exposedTypes.containsAll(focus.types());

    }

    public static FocusedClueMatch matchClue(FocusedClueSpec clue, DetectiveFocus detectiveFocus) {
        var detectiveIds = detectiveFocus.ids();
        var detectiveTypes = detectiveFocus.types();
        var focus = clue.focus;

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

        return new FocusedClueMatch(clue, eq, eqt, ne, matchedIds, matchedTypes, unmatchedTypes);
    }

    public static List<FocusedClueMatch> matchClues(List<FocusedClueSpec> clues, DetectiveFocus detectiveFocus) {
        return clues.stream()
                .map(focusedClue -> matchClue(focusedClue, detectiveFocus))
                .toList();
    }
}