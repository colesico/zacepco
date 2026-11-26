package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.dto.FocusedClueMatch;
import colesico.zacepco.investigation.model.results.*;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.investigation.model.state.Searchable;
import colesico.zacepco.script.model.scriptdoc.*;

import java.util.*;

public class SearchManager {

    private final ScriptDocument script;
    private final InvestigationState state;

    public SearchManager(InvestigationManager invManager) {
        this.script = invManager.script;
        this.state = invManager.state;
    }

    private Set<FocusedClueSpec> findUndiscoveredFocusedClues(Set<ClueSpec.Id> entityClues) {
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

    private InspectionResult searchClue(Set<ClueSpec.Id> entityClues, DetectiveFocus detectiveFocus, Integer d12) {

        Set<FocusedClueSpec> cluesToSearch = findUndiscoveredFocusedClues(entityClues);

        if (cluesToSearch.isEmpty()) {
            return CluesExhausted.of();
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

        ClueDiscovered result;
        if (selectedMatch == null) {
            // Accidental discovery: lowest threshold among undiscovered clues.
            var accidentalMatch = matchedClues.stream()
                    .min(Comparator.comparingDouble(fw -> fw.focusedClue().threshold))
                    .orElseThrow();
            var clue = script.crime.clues.get(accidentalMatch.focusedClue().clue);
            return ClueDiscovered.of(clue);
        } else {
            // Focus matched discovery
            var clue = script.crime.clues.get(selectedMatch.focusedClue().clue);
            return ClueFocusDiscovered.of(
                    clue,
                    selectedMatch.matchedIds(),
                    selectedMatch.unmatchedIds(),
                    selectedMatch.matchedTypes(),
                    selectedMatch.unmatchedTypes()
            );
        }
    }

    public InspectionResult search(TId searchedEntityId, DetectiveFocus detectiveFocus, Integer d12) {

        Searchable searchedEntity;
        Set<ClueSpec.Id> entityClues;

        if (searchedEntityId instanceof LocationSpec.Id locationId) {
            searchedEntity = state.scene.locations.get(locationId);
            entityClues = script.crime.scene.locations.get(locationId).clues;
        } else if (searchedEntityId instanceof PersonageSpec.Id personageId) {
            searchedEntity = state.personages.get(personageId);
            entityClues = script.crime.personages.get(personageId).clues;
        } else {
            throw new IllegalStateException("Unsupported searched entity: " + searchedEntityId);
        }

        if (!searchedEntity.searchAvailable()) {
            throw new IllegalArgumentException("Search not available");
        }

        // Active detective
        var detective = state.activeDetective();

        // Prepare result base values
        InspectionResult baseValues = new InspectionResult() {
        };

        baseValues.inspectionType = InspectionTypeSpec.SEARCH;
        baseValues.inspectionTarget = searchedEntityId;
        baseValues.activeDetective = detective;
        baseValues.detectiveFocus = detectiveFocus;
        baseValues.d12 = d12;
        baseValues.budgetSpend = 0;
        baseValues.currentBudget = state.budget;

        // Check inspection params
        var checkParamsResult = InvestigationUtil.checkInspectionRequest(
                detectiveFocus,
                d12,
                script,
                state,
                InspectionTypeSpec.SEARCH);

        if (checkParamsResult != null) return checkParamsResult.assign(baseValues);

        // Try to spend budget
        var cost = script.investigation.search.cost;
        if (!state.spendBudget(cost)) {
            return InsufficientBudget.of().assign(baseValues);
        }

        baseValues.budgetSpend = cost;
        baseValues.currentBudget = state.budget;

        // Perform clue search
        var result = searchClue(entityClues, detectiveFocus, d12);
        result.assign(baseValues);

        if (result instanceof CluesExhausted) {
            searchedEntity.makeUnsearchable();
            state.nextDetective();
            return result;
        }

        if (result instanceof ClueDiscovered cuResult) {

            state.clues.get(cuResult.clue.id).discovered = true;

            // Auto uncover entities
            InvestigationUtil.uncoverEntitiesByClue(cuResult.clue, state);

            // Detective awarded points
            if (result instanceof ClueFocusDiscovered cfuResult) {
                var awardedPoints = script.investigation.search.points;
                awardedPoints = awardedPoints + cfuResult.matchedIds.size();
                detective.addPoints(awardedPoints);
                cfuResult.awardedPoints = awardedPoints;
            }

            // Shift to next detective
            state.nextDetective();
        }

        return result;
    }

}