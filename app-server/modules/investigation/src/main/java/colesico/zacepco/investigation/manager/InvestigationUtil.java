package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.dto.FocusedClueMatch;
import colesico.zacepco.investigation.model.results.FocusLimitExceeded;
import colesico.zacepco.investigation.model.results.InspectionResult;
import colesico.zacepco.investigation.model.results.InvalidD12;
import colesico.zacepco.investigation.model.results.UndiscoveredFocus;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.script.model.scriptdoc.*;

import java.util.*;
import java.util.stream.Collectors;

public class InvestigationUtil {

    public static Set<TId.Type> extractIdTypes(Collection<TId> tids) {
        var types = EnumSet.noneOf(TId.Type.class);
        tids.stream().filter(Objects::nonNull).forEach(id -> types.add(id.type()));
        return types;
    }

    public static InspectionResult checkInspectionRequest(DetectiveFocus detectiveFocus,
                                                          Integer d12,
                                                          ScriptDocument script,
                                                          InvestigationState state,
                                                          InspectionTypeSpec inspectionType) {
        // Check D12
        if (d12 < 0 || d12 > 12) {
            return InvalidD12.of();
        }

        // Check focus limit
        int focusLimit = switch (inspectionType) {
            case SEARCH -> script.investigation.search.focusLimit;
            case INTERROGATION -> script.investigation.interrogation.focusLimit;
            case EXAMINATION -> script.investigation.examination.focusLimit;
        };

        if (detectiveFocus.size() > focusLimit) {
            return FocusLimitExceeded.of(focusLimit);
        }

        // Check undiscovered id/types
        var undiscoveredFocus = undiscoveredFocus(detectiveFocus, state, script);
        if (!undiscoveredFocus.isEmpty()) {
            return UndiscoveredFocus.of(undiscoveredFocus);
        }

        return null;
    }

    /**
     * Returns the part of the focus with  undiscovered entities.
     * The focus is expected to consist only of already discovered id/types,
     * so the returned part should be empty; a non-empty result
     * signals an invalid focus.
     */
    public static DetectiveFocus undiscoveredFocus(DetectiveFocus focus, InvestigationState state, ScriptDocument script) {
        Set<TId> discoveredIds = new HashSet<>(state.findDiscoveredEntities());
        discoveredIds.addAll(script.crime.timeline.keySet());

        Set<TId.Type> discoveredTypes = extractIdTypes(discoveredIds);

        var undiscoveredIds = new HashSet<>(focus.ids());
        undiscoveredIds.removeAll(discoveredIds);

        var undiscoveredTypes = EnumSet.noneOf(TId.Type.class);
        undiscoveredTypes.addAll(focus.types());
        undiscoveredTypes.removeAll(discoveredTypes);

        return new DetectiveFocus(undiscoveredIds, undiscoveredTypes);
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

    public static void uncoverEntitiesByClue(ClueSpec clue, InvestigationState state) {
        for (var tId : clue.uncovers) {
            switch (tId) {
                case ClueSpec.Id cId -> state.clues.get(cId).discovered = true;
                case LocationSpec.Id lId -> state.scene.locations.get(lId).hidden = false;
                case WaySpec.Id wId -> state.scene.ways.get(wId).hidden = false;
                case PersonageSpec.Id pId -> state.personages.get(pId).hidden = false;
                default -> throw new IllegalArgumentException("Unsupported entity to be uncovered: " + tId);
            }
        }
    }

}