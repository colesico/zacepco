package colesico.zacepco.docket.srv.service;

import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.dao.InvestigationEntryDao;
import colesico.zacepco.docket.srv.model.InvestigationEntry;
import colesico.zacepco.docket.srv.model.InvestigationStatus;
import colesico.zacepco.docket.srv.model.ScriptEntry;
import jakarta.inject.Provider;

import java.io.InputStream;

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
    public InvestigationEntry createInvestigationEntry(Long scriptEntryId) {
        Long userId = identity.get().longId();
        return createInvestigationEntry(userId, scriptEntryId);
    }

    public InvestigationEntry createInvestigationEntry(Long userId, Long scriptEntryId) {
        var scriptEntry = scriptService.findScriptEntryById(scriptEntryId);

        if (scriptEntry.isEmpty()) {
            throw new RuntimeException("Script entry not found. Id: " + scriptEntryId);
        }

        var prevInvEntry = invEntryDao.findUserInvestigationEntryById(userId, scriptEntryId);
        if (prevInvEntry.isPresent()) {
            throw new RuntimeException("Investigation on case already started by user. Id: " + scriptEntryId);
        }

        try {

            var script = scriptService.scriptPackage(scriptEntryId).script().read();

            InvestigationEntry invEntry = new InvestigationEntry();
            invEntry.setScriptEntryId(scriptEntryId);
            invEntry.setStatus(InvestigationStatus.OPEN);
            invEntry.setState(null);

            invEntry = invEntryDao.createInvestigationEntry(invEntry);

            return invEntry;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
