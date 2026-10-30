package colesico.zacepco.casework.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.service.ScriptService;
import colesico.zacepco.casework.srv.dao.InvestigationEntryDao;
import colesico.zacepco.invproc.proc.InvestigationSettings;
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



}
