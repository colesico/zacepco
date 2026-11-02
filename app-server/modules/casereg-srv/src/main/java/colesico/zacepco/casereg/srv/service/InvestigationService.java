package colesico.zacepco.casereg.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.dao.InvestigationEntryDao;

@Service
@Transactional
public class InvestigationService {

    private final ScriptService scriptService;
    private final InvestigationEntryDao invEntryDao;

    public InvestigationService(ScriptService scriptService, InvestigationEntryDao invEntryDao) {
        this.scriptService = scriptService;
        this.invEntryDao = invEntryDao;
    }

    public colesico.zacepco.casereg.srv.model.InvestigationEntry createInvestigationEntry(Long scriptEntryId) {
        var scriptEntry = scriptService.findScriptEntryById(scriptEntryId);

        if (scriptEntry.isEmpty()) {
            throw new RuntimeException("Script entry not found. Id: " + scriptEntryId);
        }

//        var prevEntry = invEntryDao.findInvestigationEntryByIdAndUser(scriptEntryId);
//        if (prevEntry.isPresent()){
//            throw new RuntimeException("Investigation on case already started by user. Id: " + scriptEntryId);
//        }

        try {

            var script = scriptService.scriptPackage(scriptEntryId).script().read();

            colesico.zacepco.casereg.srv.model.InvestigationEntry invEntry = new colesico.zacepco.casereg.srv.model.InvestigationEntry();
            invEntry.setId(scriptEntryId);
            invEntry.setStatus(colesico.zacepco.casereg.srv.model.InvestigationStatus.OPEN);
            invEntry.setState(null);

            invEntry = invEntryDao.createInvestigationEntry(invEntry);

            return invEntry;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
