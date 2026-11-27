package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.dto.FocusedClueMatch;
import colesico.zacepco.investigation.model.results.*;
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

        // Check ID items count in detective focus
        var minIdItems = switch (inspectionType) {
            case SEARCH -> 0;
            case INTERROGATION -> 1;
            case EXAMINATION -> 2;
        };

        if (detectiveFocus.ids().size() < minIdItems) {
            return EntityIdRequired.of(minIdItems);
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

        // 1-2. EQ: exact ID matches between detective focus and inspection table focus (set U)
        var matchedIds = new HashSet<TId>(detectiveIds);
        matchedIds.retainAll(focus);
        long eq = matchedIds.size();

        // 3a. Detective IDs left after EQ matches are excluded
        var unmatchedIds = new HashSet<TId>(detectiveIds);
        unmatchedIds.removeAll(matchedIds);

        // 3b. Table IDs left after EQ matches are excluded
        var remainingTableIds = new HashSet<>(focus);
        remainingTableIds.removeAll(matchedIds);

        // 4. Unique types of the remaining table IDs (set T)
        var tableTypes = EnumSet.noneOf(TId.Type.class);
        remainingTableIds.forEach(id -> tableTypes.add(id.type()));

        // 5-6. EQT: detective types matching types of the remaining table IDs (set V)
        var matchedTypes = EnumSet.noneOf(TId.Type.class);
        matchedTypes.addAll(detectiveTypes);
        matchedTypes.retainAll(tableTypes);
        long eqt = matchedTypes.size();

        // Inspection table entity types NOT matched with detective focus
        var unmatchedTypes = tableTypes.clone();
        unmatchedTypes.removeAll(matchedTypes);

        // 7. Exclude table IDs whose types are present in matchedTypes (set V)
        remainingTableIds.removeIf(id -> matchedTypes.contains(id.type()));

        // 8. NE: unmatched detective IDs + unmatched detective types + unmatched table IDs
        long ne = unmatchedIds.size()
                + (detectiveTypes.size() - eqt)
                + remainingTableIds.size();

        return new FocusedClueMatch(
                clue,
                eq,
                eqt,
                ne,
                matchedIds,
                unmatchedIds,
                matchedTypes,
                unmatchedTypes
        );
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

    public static Set<FocusedClueSpec> findUndiscoveredFocusedClues(InvestigationState state, Set<ClueSpec.Id> entityClues, Map<ClueSpec.Id, FocusedClueSpec> focusedClues) {

        Set<FocusedClueSpec> result = new HashSet<>();
        for (var clueId : entityClues) {
            var clueState = state.clues.get(clueId);
            if (clueState.discovered) {
                continue;
            }
            var focusedClue = focusedClues.get(clueId);
            if (focusedClue == null) {
                continue;
            }
            result.add(focusedClue);
        }

        return result;
    }

}