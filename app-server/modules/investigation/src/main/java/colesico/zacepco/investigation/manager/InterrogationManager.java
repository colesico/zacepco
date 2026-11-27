package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.results.CluesExhausted;
import colesico.zacepco.investigation.model.results.InspectionResult;
import colesico.zacepco.investigation.model.results.InsufficientBudget;
import colesico.zacepco.investigation.model.results.InterrogationClue;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.investigation.model.state.PersonageState;
import colesico.zacepco.script.model.scriptdoc.*;

import java.util.Set;

public class InterrogationManager {

    private final ScriptDocument script;
    private final InvestigationState state;

    public InterrogationManager(InvestigationManager invManager) {
        this.script = invManager.script;
        this.state = invManager.state;
    }

    private InspectionResult performInterrogation(Set<ClueSpec.Id> personageClues, DetectiveFocus detectiveFocus, Integer d12) {
        return null;
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
