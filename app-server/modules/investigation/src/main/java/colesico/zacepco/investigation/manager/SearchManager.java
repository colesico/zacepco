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


    private InspectionResult performSearch(Set<ClueSpec.Id> entityClues, DetectiveFocus detectiveFocus, Integer d12) {

        Set<FocusedClueSpec> cluesToSearch = InvestigationUtil.findUndiscoveredFocusedClues(state, entityClues, script.investigation.search.clues);

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

        if (selectedMatch == null) {
            // Accidental discovery: lowest threshold among undiscovered clues.
            var accidentalMatch = matchedClues.stream()
                    .min(Comparator.comparingDouble(fw -> fw.focusedClue().threshold))
                    .orElseThrow();
            var clue = script.crime.clues.get(accidentalMatch.focusedClue().clue);
            return SearchClue.of(clue);
        } else {
            // Focus matched discovery
            var clue = script.crime.clues.get(selectedMatch.focusedClue().clue);
            return SearchFocusedClue.of(
                    clue,
                    selectedMatch.matchedIds(),
                    selectedMatch.matchedTypes(),
                    selectedMatch.unmatchedTypes()
            );
        }
    }

    /**
     * Search on entity
     *
     * @param entityId       entity to be searched
     * @param detectiveFocus
     * @param d12
     */
    public InspectionResult search(TId entityId, DetectiveFocus detectiveFocus, Integer d12) {

        Searchable entity;
        Set<ClueSpec.Id> entityClues;

        if (entityId instanceof LocationSpec.Id locationId) {
            entity = state.scene.locations.get(locationId);
            entityClues = script.crime.scene.locations.get(locationId).clues;
        } else if (entityId instanceof PersonageSpec.Id personageId) {
            entity = state.personages.get(personageId);
            entityClues = script.crime.personages.get(personageId).clues;
        } else {
            throw new IllegalStateException("Unsupported searched entity: " + entityId);
        }

        if (!entity.searchAvailable()) {
            throw new IllegalArgumentException("Search not available for entity: " + entityId);
        }

        // Active detective
        var detective = state.activeDetective();

        // Prepare result base values
        InspectionResult baseValues = InspectionResult.of()
                .inspectionType(InspectionTypeSpec.SEARCH)
                .inspectionTarget(entityId)
                .activeDetective(detective)
                .detectiveFocus(detectiveFocus)
                .d12(d12)
                .budgetSpend(0)
                .currentBudget(state.budget);

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
        var result = performSearch(entityClues, detectiveFocus, d12);
        result.assign(baseValues);

        if (result instanceof CluesExhausted) {
            entity.makeUnsearchable();
            state.nextDetective();
            return result;
        }

        if (result instanceof SearchClue scdResult) {

            state.clues.get(scdResult.clue.id).discovered = true;

            // Auto uncover entities
            InvestigationUtil.uncoverEntitiesByClue(scdResult.clue, state);

            // Detective awarded points
            if (result instanceof SearchFocusedClue sfcdResult) {
                var awardedPoints = script.investigation.search.points;
                awardedPoints = awardedPoints + sfcdResult.matchedIds.size();
                detective.addPoints(awardedPoints);
                sfcdResult.awardedPoints = awardedPoints;
            }

            // Shift to next detective
            state.nextDetective();
        }

        return result;
    }

}