package colesico.zacepco.casework.srv.dao;

import colesico.framework.jdbirec.RecordView;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.model.ScriptEntry;
import colesico.zacepco.casework.srv.model.InvestigationEntry;
import jakarta.inject.Named;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.Optional;

@Service
@Transactional
public class InvestigationEntryDao {

    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    private final InvestigationEntryRK invEntryFullRk;
    private final InvestigationEntryRK invEntryBriefRk;

    public InvestigationEntryDao(Provider<Handle> handle,
                                 @Named(RecordView.VIEW_FULL) InvestigationEntryRK invEntryFullRk,
                                 @Named(RecordView.VIEW_BRIEF) InvestigationEntryRK invEntryBriefRk) {

        this.handle = handle;
        this.invEntryFullRk = invEntryFullRk;
        this.invEntryBriefRk = invEntryBriefRk;
    }

    public InvestigationEntry createInvestigationEntry(InvestigationEntry invEntry) {
        String sql = invEntryFullRk.sql("insert into @table (@columns) values (@values)");

        Long id = handle.get()
                .createUpdate(sql)
                .bindMap(invEntryFullRk.map(invEntry))
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Long.class)
                .one();

        invEntry.setId(id);

        return invEntry;
    }

    public Optional<InvestigationEntry> findInvestigationEntryById(Long id) {
        String sql = invEntryFullRk.sql("select @columns from @records where id = :id");
        return handle.get().createQuery(sql).bind("id", id).map(invEntryFullRk.mapper()).findOne();
    }

}
