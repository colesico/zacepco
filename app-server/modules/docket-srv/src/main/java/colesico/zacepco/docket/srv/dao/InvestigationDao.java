package colesico.zacepco.docket.srv.dao;

import colesico.framework.jdbirec.RecordView;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.model.Investigation;
import jakarta.inject.Named;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.Optional;

@Service
@Transactional
public class InvestigationDao {

    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    private final InvestigationRK investigationFullRk;
    private final InvestigationRK investigationBriefRk;

    public InvestigationDao(Provider<Handle> handle,
                            @Named(RecordView.VIEW_FULL) InvestigationRK investigationFullRk,
                            @Named(RecordView.VIEW_BRIEF) InvestigationRK investigationBriefRk) {

        this.handle = handle;
        this.investigationFullRk = investigationFullRk;
        this.investigationBriefRk = investigationBriefRk;
    }

    public Investigation createInvestigation(Investigation inv) {
        String sql = investigationFullRk.sql("insert into @table (@columns) values (@values)");

        Long id = handle.get()
                .createUpdate(sql)
                .bindMap(investigationFullRk.map(inv))
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Long.class)
                .one();

        inv.setId(id);

        return inv;
    }

    public Optional<Investigation> findInvestigationById(Long id) {
        String sql = investigationFullRk.sql("select @columns from @table where id = :id");
        return handle.get().createQuery(sql).bind("id", id).map(investigationFullRk.mapper()).findOne();
    }

    public Optional<Investigation> findUserInvestigation(Long userId, Long scriptId) {
        String sql = investigationFullRk.sql("select @columns from @table where user_id = :userId and script_id = :scriptId");
        return handle.get()
                .createQuery(sql)
                .bind("userId", userId)
                .bind("scriptId", scriptId)
                .map(investigationFullRk.mapper())
                .findOne();
    }
}
