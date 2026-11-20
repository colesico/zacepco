package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.*;
import colesico.zacepco.investigation.dto.action.*;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.investigation.model.state.Searchable;
import colesico.zacepco.script.model.scriptdoc.*;

import java.util.*;

public class SearchManager {

    private final ScriptDocument script;
    private final InvestigationState state;

    private final TId entityId;

    public SearchManager(InvestigationManager invManager, TId entityId) {
        this.script = invManager.script;
        this.state = invManager.state;
        this.entityId = entityId;
    }

    private Set<FocusedClueSpec> findUnexposedFocusedClues(Set<ClueSpec.Id> entityClues) {
        var focusedClues = script.investigation.search.clues;

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

    private LocationSearchResult searchClue(Set<ClueSpec.Id> entityClues, DetectiveFocus detectiveFocus, Integer d12) {
        Set<FocusedClueSpec> cluesToSearch = findUnexposedFocusedClues(entityClues);

        if (cluesToSearch.isEmpty()) {
            return new CluesExhausted();
        }

        var matchedClues = InvestigationUtil.matchClues(cluesToSearch, detectiveFocus);

        // Order: EQ desc, EQT desc, NE asc, threshold desc
        matchedClues.sort(
                Comparator.comparingLong(FocusedClueMatch::eq).reversed()
                        .thenComparing(Comparator.comparingLong(FocusedClueMatch::eqt).reversed())
                        .thenComparingLong(FocusedClueMatch::ne)
                        .thenComparing(Comparator.comparingDouble(
                                (FocusedClueMatch fw) -> fw.focusedClue().threshold).reversed())
        );

        double rf = state.randFactor;

        // Find first accepted
        FocusedClueMatch selectedMatch = null;
        for (var mc : matchedClues) {
            if ((mc.eq() > 0 || mc.eqt() > 0) && (d12 >= mc.focusedClue().threshold * rf)) {
                selectedMatch = mc;
                break;
            }
        }

        ClueUncover result;
        if (selectedMatch == null) {
            // Accidental discovery: lowest threshold among unexposed clues.
            var accidentalMatch = matchedClues.stream()
                    .min(Comparator.comparingDouble(fw -> fw.focusedClue().threshold))
                    .orElseThrow();
            var clue = script.crime.clues.get(accidentalMatch.focusedClue().clue);
            return new ClueUncover(clue, matchedClues.size() - 1, 0);
        } else {
            // Focus matched discovery
            var clue = script.crime.clues.get(selectedMatch.focusedClue().clue);
            return new ClueFocusedUncover(
                    clue,
                    selectedMatch.matchedIds(),
                    selectedMatch.matchedTypes(),
                    selectedMatch.unmatchedTypes(),
                    matchedClues.size() - 1,
                    0
            );
        }
    }

    public LocationSearchResult search(DetectiveFocus detectiveFocus, Integer d12) {

        if (state.budget <= 0) {
            throw new IllegalArgumentException("Out of investigation budget" + entityId);
        }

        Searchable searchedEntity;
        Set<ClueSpec.Id> entityClues;

        if (entityId instanceof LocationSpec.Id locationId) {
            searchedEntity = state.scene.locations.get(locationId);
            entityClues = script.crime.scene.locations.get(locationId).clues;
        } else if (entityId instanceof PersonageSpec.Id personageId) {
            searchedEntity = state.personages.get(personageId);
            entityClues = script.crime.personages.get(personageId).clues;
        } else {
            throw new IllegalStateException("Unsupported entityId: " + entityId);
        }

        if (!searchedEntity.searchAvailable()) {
            throw new IllegalArgumentException("Search not available");
        }

        // Check params
        var res = InvestigationUtil.checkActionRequest(detectiveFocus, d12, script, state);
        if (res != null) return res;

        // Try to spend budget
        var cost = script.investigation.search.cost;
        if (!state.spendBudget(cost)) {
            return new InsufficientBudget();
        }

        // Perform clue search
        var result = searchClue(entityClues, detectiveFocus, d12);

        if (result instanceof CluesExhausted) {
            searchedEntity.makeUnsearchable();
            state.nextDetective();
            return result;
        }

        if (result instanceof ClueUncover cuResult) {

            state.clues.get(cuResult.clue.id).discovered = true;

            if (cuResult.cluesRemaining == 0) {
                searchedEntity.makeUnsearchable();
            }

            // Detective awarded points
            if (result instanceof ClueFocusedUncover cfuResult) {
                var detective = state.activeDetective();
                var awardedPoints = script.investigation.search.points;
                awardedPoints = awardedPoints + cfuResult.matchedIds.size();
                detective.addPoints(awardedPoints);
                cfuResult.awardedPoints = awardedPoints;
            }

            // Shift to next detective
            state.nextDetective();

            // Auto reveal entities
            InvestigationUtil.revealEntitiesByClue(cuResult.clue, state);
        }

        return result;
    }

}