package colesico.zacepco.casework.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.service.ScriptService;
import colesico.zacepco.casework.srv.dao.InvestigationEntryDao;
import colesico.zacepco.casework.srv.dto.InvestigationSettings;
import colesico.zacepco.casework.srv.model.*;
import colesico.zacepco.scriptpack.model.script.DetectiveSpec;
import colesico.zacepco.scriptpack.model.script.LocationSpec;
import colesico.zacepco.scriptpack.model.script.ScriptDocument;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvestigationService {

    private final ScriptService scriptService;
    private final InvestigationEntryDao invEntryDao;

    public InvestigationService(ScriptService scriptService, InvestigationEntryDao invEntryDao) {
        this.scriptService = scriptService;
        this.invEntryDao = invEntryDao;
    }

    public InvestigationEntry createInvestigationEntry(Long scriptEntryId, InvestigationSettings invSettings) {
        var scriptEntry = scriptService.findScriptEntryById(scriptEntryId);

        if (scriptEntry.isEmpty()) {
            throw new RuntimeException("Script entry not found. Id: " + scriptEntryId);
        }

        var prevEntry = invEntryDao.findInvestigationEntryByIdAndUser(scriptEntryId);
        if (prevEntry.isPresent()){
            throw new RuntimeException("Investigation on case already started by user. Id: " + scriptEntryId);
        }

        try {

            var script = scriptService.scriptPackage(scriptEntryId).script().read();

            InvestigationEntry invEntry = new InvestigationEntry();
            invEntry.setId(scriptEntryId);
            invEntry.setStatus(InvestigationStatus.OPEN);
            invEntry.setState(buildInvestigationState(script, invSettings));

            invEntry = invEntryDao.createInvestigationEntry(invEntry);

            return invEntry;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private InvestigationState buildInvestigationState(ScriptDocument script, InvestigationSettings invSettings) {
        var invState = new InvestigationState();

        invState.setBudget(script.investigation.budget);
        invState.setRandFactor(invSettings.randFactor != null ? invSettings.randFactor : script.investigation.randFactor);

        // Build detectives state

        List<DetectiveSpec> allDetectives = new ArrayList<>(script.investigation.detectives);
        Collections.shuffle(allDetectives, new Random());
        var randomDetectives = allDetectives.subList(0, Math.min(invSettings.detectivesNum, allDetectives.size()));

        Map<DetectiveSpec.Id, DetectiveState> detectiveState = randomDetectives.stream()
                .collect(Collectors.toMap(
                        DetectiveSpec::getId,
                        det -> new DetectiveState(det.id)        // Значение: сама сущность
                ));

        invState.detectives.putAll(detectiveState);

        // Build locations state

        Map<LocationSpec.Id, LocationState> locationState = script.crime.scene.locations.stream()
                .collect(Collectors.toMap(
                        LocationSpec::getId,
                        loc -> {
                            var locState = new LocationState(loc.id);
                            locState.exposedClues.addAll(loc.exposedClues);
                            return locState;
                        }
                ));

        invState.locations.putAll(locationState);


        return invState;
    }
}
