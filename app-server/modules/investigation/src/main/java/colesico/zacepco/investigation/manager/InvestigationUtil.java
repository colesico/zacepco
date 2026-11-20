package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.action.LocationSearchResult;
import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.dto.FocusedClueMatch;
import colesico.zacepco.investigation.dto.action.InvalidD12;
import colesico.zacepco.investigation.dto.action.UnrevealedFocus;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.*;

import java.util.*;
import java.util.stream.Collectors;

public class InvestigationUtil {

    public static boolean checkD12(int d12) {
        return d12 > 0 && d12 <= 12;
    }

    public static Set<TId.Type> extractIdTypes(Collection<TId> tids) {
        var types = EnumSet.noneOf(TId.Type.class);
        tids.stream().filter(Objects::nonNull).forEach(id -> types.add(id.type()));
        return types;
    }

    /**
     * Returns the part of the focus not yet covered by exposed entities
     * The focus is expected to consist only of already exposed entities,
     * so the returned uncovered part should be empty; a non-empty result
     * signals an invalid focus.
     */
    public static DetectiveFocus uncoveredFocus(DetectiveFocus focus, InvestigationState state, ScriptDocument script) {
        Set<TId> exposedIds = new HashSet<>(state.findExposedEntities());
        exposedIds.addAll(script.crime.timeline.keySet());

        Set<TId.Type> exposedTypes = extractIdTypes(exposedIds);

        var uncoveredIds = new HashSet<>(focus.ids());
        uncoveredIds.removeAll(exposedIds);

        var uncoveredTypes = EnumSet.noneOf(TId.Type.class);
        uncoveredTypes.addAll(focus.types());
        uncoveredTypes.removeAll(exposedTypes);

        return new DetectiveFocus(uncoveredIds, uncoveredTypes);
    }

    public static LocationSearchResult checkActionRequest(DetectiveFocus detectiveFocus, Integer d12, ScriptDocument script, InvestigationState state) {
        if (!checkD12(d12)) {
            return new InvalidD12();
        }

        var uncoveredFocus = uncoveredFocus(detectiveFocus, state, script);
        if (!uncoveredFocus.isEmpty()) {
            return new UnrevealedFocus(uncoveredFocus);
        }

        return null;
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

    public static List<FocusedClueMatch> matchClues(Set<FocusedClueSpec> clues, DetectiveFocus detectiveFocus) {
        return clues.stream()
                .map(focusedClue -> matchClue(focusedClue, detectiveFocus))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void revealEntitiesByClue(ClueSpec clue, InvestigationState state) {
        for (var tId : clue.reveals) {
            switch (tId) {
                case ClueSpec.Id cId -> state.clues.get(cId).discovered = true;
                case LocationSpec.Id lId -> state.scene.locations.get(lId).hidden = false;
                case WaySpec.Id wId -> state.scene.ways.get(wId).hidden = false;
                case PersonageSpec.Id pId -> state.personages.get(pId).hidden = false;
                default -> throw new IllegalArgumentException("Unsupported revealed entity: " + tId);
            }
        }
    }
}