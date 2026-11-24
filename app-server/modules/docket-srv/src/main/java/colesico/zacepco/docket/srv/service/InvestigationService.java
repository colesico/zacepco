package colesico.zacepco.docket.srv.service;

import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.dao.InvestigationDao;
import colesico.zacepco.docket.srv.model.Investigation;
import colesico.zacepco.docket.srv.model.InvestigationStatus;
import colesico.zacepco.docket.srv.model.ScriptStatus;
import colesico.zacepco.docket.srv.model.dto.CreateInvestigationRequest;
import colesico.zacepco.investigation.manager.InvestigationManager;
import colesico.zacepco.investigation.manager.InvestigationStateBuilder;
import colesico.zacepco.investigation.manager.InvestigationStateEnricher;
import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.results.ClueDiscovered;
import colesico.zacepco.investigation.model.results.CluesExhausted;
import colesico.zacepco.investigation.model.results.InspectionResult;
import colesico.zacepco.script.model.scriptdoc.TId;
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
        var script = scriptService.findScriptById(request.scriptId).orElseThrow(() -> new RuntimeException("Script not found. Id: " + request.scriptId));

        if (script.status.equals(ScriptStatus.ARCHIVED)) {
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
        var inv = investigationDao.findInvestigationById(id);
        if (inv.isEmpty()) {
            return Optional.empty();
        }

        var investigation = inv.get();

        var scriptDoc = scriptService.scriptPackage(investigation.getScriptId()).scriptDocument().read();

        InvestigationStateEnricher enricher = new InvestigationStateEnricher(investigation.getState(), scriptDoc);
        investigation.setState(enricher.enrich());

        return inv;
    }

    public InspectionResult search(Long investigationId, TId entityId, DetectiveFocus detectiveFocus, Integer d12) {

        var investigation = investigationDao.findInvestigationById(investigationId).orElseThrow(() -> new IllegalArgumentException("Investigation not found. Id: " + investigationId));
        var script = scriptService.scriptPackage(investigation.getScriptId()).scriptDocument().read();

        var invManager = new InvestigationManager(script, investigation.state);

        var result = invManager.searchManager().search(entityId, detectiveFocus, d12);

        if (result instanceof ClueDiscovered
                || result instanceof CluesExhausted) {
            investigationDao.updateInvestigationState(investigationId, invManager.state);
        }

        return result;
    }
}
