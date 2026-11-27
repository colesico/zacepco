package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.dto.FocusedClueMatch;
import colesico.zacepco.investigation.model.results.*;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.investigation.model.state.PersonageState;
import colesico.zacepco.script.model.scriptdoc.*;

import java.util.Comparator;
import java.util.Set;

public class InterrogationManager {

    private final ScriptDocument script;
    private final InvestigationState state;

    public InterrogationManager(InvestigationManager invManager) {
        this.script = invManager.script;
        this.state = invManager.state;
    }

    private InspectionResult performInterrogation(Set<ClueSpec.Id> personageClues, DetectiveFocus detectiveFocus, Integer d12) {

        // Undiscovered focused clues for this personage
        Set<FocusedClueSpec> cluesToInterrogate = InvestigationUtil.findUndiscoveredFocusedClues(
                state,
                personageClues,
                script.investigation.interrogation.clues
        );

        // Interrogation table exhausted for this personage
        if (cluesToInterrogate.isEmpty()) {
            return CluesExhausted.of();
        }

        var matchedClues = InvestigationUtil.matchClues(cluesToInterrogate, detectiveFocus);

        // Order: EQ desc, EQT desc, NE asc, threshold asc
        matchedClues.sort(
                Comparator.comparingLong(FocusedClueMatch::eq).reversed()
                        .thenComparing(Comparator.comparingLong(FocusedClueMatch::eqt).reversed())
                        .thenComparingLong(FocusedClueMatch::ne)
                        .thenComparingDouble((FocusedClueMatch m) -> m.focusedClue().threshold)
        );

        // Target row — first after ordering
        var selectedMatch = matchedClues.getFirst();

        // EQ = 0: personage knows nothing about what the detective is asking about.
        // All detective IDs are reported as unmatched.
        if (selectedMatch.eq() == 0) {
            return new InterrogationUnproductive(
                    detectiveFocus.ids(),
                    selectedMatch.matchedTypes(),
                    selectedMatch.unmatchedTypes()
            );
        }

        double rf = state.randFactor;

        // EQ > 0: personage knows about the focus; D12 vs threshold decides if the clue opens
        if (d12 >= selectedMatch.focusedClue().threshold * rf) {
            var clue = script.crime.clues.get(selectedMatch.focusedClue().clue);
            return new InterrogationClue(
                    clue,
                    selectedMatch.matchedIds(),
                    selectedMatch.matchedTypes(),
                    selectedMatch.unmatchedTypes()
            );
        }

        // EQ > 0 but D12 < threshold — personage knows but won't reveal details
        return new InterrogationIncomplete(
                selectedMatch.matchedIds(),
                selectedMatch.matchedTypes(),
                selectedMatch.unmatchedTypes()
        );
    }

    public InspectionResult interrogate(PersonageSpec.Id personageId, DetectiveFocus detectiveFocus, Integer d12) {
        PersonageState personage = state.personages.get(personageId);

        Set<ClueSpec.Id> personageClues = script.crime.personages.get(personageId).clues;

        if (!personage.interrogationAvailable()) {
            throw new IllegalArgumentException("Interrogation not available for personage: " + personageId);
        }

        // Active detective
        var detective = state.activeDetective();

        // Prepare result base values
        InspectionResult baseValues = InspectionResult.of()
                .inspectionType(InspectionTypeSpec.SEARCH)
                .inspectionTarget(personageId)
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

        // Perform interrogation
        var result = performInterrogation(personageClues, detectiveFocus, d12);
        result.assign(baseValues);

        if (result instanceof CluesExhausted) {
            personage.makeUninterrogatable();
            state.nextDetective();
            return result;
        }

        if (result instanceof InterrogationClue icdResult) {
            state.clues.get(icdResult.clue.id).discovered = true;

            // Auto uncover entities
            InvestigationUtil.uncoverEntitiesByClue(icdResult.clue, state);

            // Detective avarded
            var awardedPoints = script.investigation.search.points;
            awardedPoints = awardedPoints + icdResult.matchedIds.size();
            detective.addPoints(awardedPoints);
            icdResult.awardedPoints = awardedPoints;

            // Shift to next detective
            state.nextDetective();
        }

        return result;
    }
}
