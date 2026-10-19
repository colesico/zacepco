package colesico.zacepco.casework.srv.dao;

import colesico.framework.jdbirec.RecordView;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import jakarta.inject.Named;

@Service
@Transactional
public class InvestigationEntryDao {
    private final InvestigationEntryRK invEntryFullRk;
    private final InvestigationEntryRK invEntryBriefRk;

    public InvestigationEntryDao(@Named(RecordView.VIEW_FULL) InvestigationEntryRK invEntryFullRk,
                                 @Named(RecordView.VIEW_BRIEF) InvestigationEntryRK invEntryBriefRk) {

        this.invEntryFullRk = invEntryFullRk;
        this.invEntryBriefRk = invEntryBriefRk;
    }
}
