package colesico.zacepco.cases.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.cases.srv.model.CaseFile;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CaseFileDao {
    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    /**
     * Case file Record Kit
     */
    private final CaseFileRk caseFileRk;

    public CaseFileDao(Provider<Handle> handle, CaseFileRk caseFileRk) {
        this.handle = handle;
        this.caseFileRk = caseFileRk;
    }

    public Long createCaseFileId() {
        var sql = "UPDATE cases_id_sequence SET value = value + 1 WHERE id = 1 RETURNING value";
        return handle.get().createQuery(sql).mapTo(Long.class).one();
    }

    public void createCaseFile(CaseFile caseFile) {
        String sql = caseFileRk.sql("insert into @table (@columns) values (@values)");
        handle.get().createUpdate(sql).bindMap(caseFileRk.map(caseFile)).execute();
    }

    public Optional<CaseFile> findCaseFileById(Long id) {
        String sql = caseFileRk.sql("select @columns from @records where id = :id");
        return handle.get().createQuery(sql).bind("id", id).map(caseFileRk.mapper()).findOne();
    }

    public Optional<CaseFile> findCaseFileByScriptId(String scriptId) {
        String sql = caseFileRk.sql("select @columns from @records where script_id = :scriptId");
        return handle.get().createQuery(sql).bind("scriptId", scriptId).map(caseFileRk.mapper()).findOne();
    }

    public List<CaseFile> listScriptFiles(int limit, long offset) {
        var query = """
                select @record
                from @table
                order by id desc
                limit :limit offset :offset
                """;
        var handle = this.handle.get();
        String sql = caseFileRk.sql(query);
        return handle.createQuery(sql)
                .bind("limit", limit)
                .bind("offset", offset)
                .map(caseFileRk.mapper())
                .list();

    }
}
