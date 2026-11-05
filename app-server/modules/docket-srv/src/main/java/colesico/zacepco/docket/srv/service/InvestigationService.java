package colesico.zacepco.docket.srv.service;

import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.dao.InvestigationEntryDao;
import colesico.zacepco.docket.srv.dto.CreateInvestigationRequest;
import colesico.zacepco.docket.srv.model.InvestigationEntry;
import colesico.zacepco.docket.srv.model.InvestigationStatus;
import colesico.zacepco.investigation.service.InvestigationStateBuilder;
import jakarta.inject.Provider;

import java.util.Date;

@Service
@Transactional
public class InvestigationService {

    private final ScriptService scriptService;
    private final InvestigationEntryDao invEntryDao;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public InvestigationService(ScriptService scriptService,
                                InvestigationEntryDao invEntryDao,
                                Provider<Identity> identity) {

        this.scriptService = scriptService;
        this.invEntryDao = invEntryDao;
        this.identity = identity;
    }

    @RequireIdentity
    public InvestigationEntry createInvestigationEntry(CreateInvestigationRequest request) {
        Long userId = identity.get().longId();
        return createInvestigationEntry(userId, request);
    }

    public InvestigationEntry createInvestigationEntry(Long userId, CreateInvestigationRequest request) {
        var scriptEntry = scriptService.findScriptEntryById(request.scriptEntryId);

        if (scriptEntry.isEmpty()) {
            throw new RuntimeException("Script entry not found. Id: " + request.scriptEntryId);
        }

        var prevInvEntry = invEntryDao.findUserInvestigationEntryById(userId, request.scriptEntryId);
        if (prevInvEntry.isPresent()) {
            throw new RuntimeException("Investigation on case already started by user. Id: " + request.scriptEntryId);
        }

        try {

            var script = scriptService.scriptPackage(request.scriptEntryId).script().read();

            InvestigationEntry invEntry = new InvestigationEntry();
            invEntry.setUserId(userId);
            invEntry.setScriptEntryId(request.scriptEntryId);
            invEntry.setStatus(InvestigationStatus.OPEN);
            invEntry.setCreatedAt(new Date());


            var stateBuilder = new InvestigationStateBuilder(script);
            stateBuilder
                    .detectivesNum(request.detectivesNum)
                    .randFactor(request.randFactor)
                    .budgetScale(request.budgetScale);

            invEntry.setState(stateBuilder.build());

            invEntry = invEntryDao.createInvestigationEntry(invEntry);

            return invEntry;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
