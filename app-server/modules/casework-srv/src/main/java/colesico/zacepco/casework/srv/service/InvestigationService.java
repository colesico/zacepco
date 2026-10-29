package colesico.zacepco.casework.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.dao.ScriptEntryDaoServiceProxy;
import colesico.zacepco.casereg.srv.model.ScriptEntry;
import colesico.zacepco.casereg.srv.service.ScriptService;
import colesico.zacepco.casework.srv.dao.InvestigationEntryDao;
import colesico.zacepco.casework.srv.model.InvestigationEntry;
import colesico.zacepco.casework.srv.model.InvestigationStatus;

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
        invEntry.setStatus(InvestigationStatus.OPEN);

        invEntry = invEntryDao.createInvestigationEntry(invEntry);

        return invEntry;
    }


}
