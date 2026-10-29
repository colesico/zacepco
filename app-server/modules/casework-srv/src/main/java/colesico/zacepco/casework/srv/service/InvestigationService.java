package colesico.zacepco.casework.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.service.ScriptService;
import colesico.zacepco.casework.srv.dao.InvestigationEntryDao;
import colesico.zacepco.casework.srv.dto.InvestigationSettings;
import colesico.zacepco.casework.srv.model.*;
import colesico.zacepco.scriptpack.model.script.Detective;
import colesico.zacepco.scriptpack.model.script.Location;
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

    public InvestigationEntry createInvestigationEntry(Long scriptEntryId) {
        var scriptEntry = scriptService.findScriptEntryById(scriptEntryId);

        if (scriptEntry.isEmpty()) {
            throw new RuntimeException("Script entry not found. Id: " + scriptEntryId);
        }

        InvestigationEntry invEntry = new InvestigationEntry();
        invEntry.setScriptEntryId(scriptEntryId);
        invEntry.setStatus(InvestigationStatus.OPEN);

        invEntry = invEntryDao.createInvestigationEntry(invEntry);

        return invEntry;
    }


    private InvestigationState buildInvestigationState(ScriptDocument script, InvestigationSettings invSettings) {
        var invState = new InvestigationState();

        // Build detectives state

        List<Detective> allDetectives = new ArrayList<>(script.investigation.detectives);
        Collections.shuffle(allDetectives, new Random());
        var randomDetectives = allDetectives.subList(0, Math.min(invSettings.detectivesNum, allDetectives.size()));

        Map<Detective.Id, DetectiveState> detectiveState = randomDetectives.stream()
                .collect(Collectors.toMap(
                        Detective::getId,
                        det -> new DetectiveState(det.id)        // Значение: сама сущность
                ));

        invState.detectives.putAll(detectiveState);

        // Build locations state

        Map<Location.Id, LocationState> locationState = script.crime.scene.locations.stream()
                .collect(Collectors.toMap(
                        Location::getId,
                        loc -> {
                            var locState = new LocationState(loc.id);
                            locState.exposedClues.addAll(loc.exposedClues);
                            return locState;
                        }
                ));
        return invState;
    }
}
