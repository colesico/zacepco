package colesico.zacepco.docket.srv.service;

import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.dao.InvestigationDao;
import colesico.zacepco.docket.srv.dto.CreateInvestigationRequest;
import colesico.zacepco.docket.srv.model.Investigation;
import colesico.zacepco.docket.srv.model.InvestigationStatus;
import colesico.zacepco.docket.srv.model.ScriptStatus;
import colesico.zacepco.investigation.manager.InvestigationStateBuilder;
import colesico.zacepco.investigation.manager.InvestigationStateEnricher;
import jakarta.inject.Provider;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class InvestigationService {

    private final ScriptService scriptService;
    private final InvestigationDao investigationDao;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public InvestigationService(ScriptService scriptService,
                                InvestigationDao investigationDao,
                                Provider<Identity> identity) {

        this.scriptService = scriptService;
        this.investigationDao = investigationDao;
        this.identity = identity;
    }

    @RequireIdentity
    public Investigation createInvestigation(CreateInvestigationRequest request) {
        Long userId = identity.get().longId();
        return createInvestigation(userId, request);
    }

    public Investigation createInvestigation(Long userId, CreateInvestigationRequest request) {
        var script = scriptService.findScriptById(request.scriptId);

        if (script.isEmpty()) {
            throw new RuntimeException("Script not found. Id: " + request.scriptId);
        }

        if (script.get().status.equals(ScriptStatus.ARCHIVED)) {
            throw new RuntimeException("Script is archived");
        }

        var prevInvEntry = investigationDao.findUserInvestigation(userId, request.scriptId);
        if (prevInvEntry.isPresent()) {
            throw new RuntimeException("Investigation on script already started by user. Id: " + request.scriptId);
        }


        var scriptDoc = scriptService.scriptPackage(request.scriptId).scriptDocument().read();

        Investigation investigation = new Investigation();
        investigation.setUserId(userId);
        investigation.setScriptId(request.scriptId);
        investigation.setStatus(InvestigationStatus.OPEN);
        investigation.setCreatedAt(new Date());


        var stateBuilder = new InvestigationStateBuilder(scriptDoc);
        stateBuilder
                .detectivesNum(request.detectivesNum)
                .randFactor(request.randFactor)
                .budgetScale(request.budgetScale);

        investigation.setState(stateBuilder.build());

        investigation = investigationDao.createInvestigation(investigation);

        return investigation;

    }

    public Optional<Investigation> findUserInvestigation(Long userId, Long scriptEntryId) {
        return investigationDao.findUserInvestigation(userId, scriptEntryId);
    }

    public Optional<Investigation> findInvestigationById(Long id) {
        var invEntryOpt = investigationDao.findInvestigationById(id);
        if (invEntryOpt.isEmpty()) {
            return Optional.empty();
        }

        var invEntry = invEntryOpt.get();

        var scriptDoc = scriptService.scriptPackage(invEntry.getScriptId()).scriptDocument().read();

        InvestigationStateEnricher enricher = new InvestigationStateEnricher(invEntry.getState(), scriptDoc);
        invEntry.setState(enricher.enrich());

        return invEntryOpt;
    }
}
