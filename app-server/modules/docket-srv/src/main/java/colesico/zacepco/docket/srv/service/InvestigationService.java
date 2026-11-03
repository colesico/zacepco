package colesico.zacepco.docket.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.dao.InvestigationEntryDao;
import colesico.zacepco.docket.srv.model.InvestigationEntry;
import colesico.zacepco.docket.srv.model.InvestigationStatus;

@Service
@Transactional
public class InvestigationService {

    private final ScriptService scriptService;
    private final InvestigationEntryDao invEntryDao;

    public InvestigationService(ScriptService scriptService, InvestigationEntryDao invEntryDao) {
        this.scriptService = scriptService;
        this.invEntryDao = invEntryDao;
    }

    public InvestigationEntry createInvestigationEntry(Long userId, Long scriptEntryId) {
        var scriptEntry = scriptService.findScriptEntryById(scriptEntryId);

        if (scriptEntry.isEmpty()) {
            throw new RuntimeException("Script entry not found. Id: " + scriptEntryId);
        }

        var prevEntry = invEntryDao.findUserInvestigationById(scriptEntryId);
        if (prevEntry.isPresent()) {
            throw new RuntimeException("Investigation on case already started by user. Id: " + scriptEntryId);
        }

        try {

            var script = scriptService.scriptPackage(scriptEntryId).script().read();

            InvestigationEntry invEntry = new InvestigationEntry();
            invEntry.setId(scriptEntryId);
            invEntry.setStatus(InvestigationStatus.OPEN);
            invEntry.setState(null);

            invEntry = invEntryDao.createInvestigationEntry(invEntry);

            return invEntry;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
