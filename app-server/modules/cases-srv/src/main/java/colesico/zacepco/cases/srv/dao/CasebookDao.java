package colesico.zacepco.cases.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.cases.srv.model.Casebook;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CasebookDao {
    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    /**
     * Case file Record Kit
     */
    private final CasebookRk casebookRk;

    public CasebookDao(Provider<Handle> handle, CasebookRk casebookRk) {
        this.handle = handle;
        this.casebookRk = casebookRk;
    }

    public Long createCasebookId() {
        var sql = "UPDATE casebooks_id_sequence SET value = value + 1 WHERE id = 1 RETURNING value";
        return handle.get().createQuery(sql).mapTo(Long.class).one();
    }

    public void createCasebook(Casebook casebook) {
        String sql = casebookRk.sql("insert into @table (@columns) values (@values)");
        handle.get().createUpdate(sql).bindMap(casebookRk.map(casebook)).execute();
    }

    public Optional<Casebook> findCasebookById(Long id) {
        String sql = casebookRk.sql("select @columns from @records where id = :id");
        return handle.get().createQuery(sql).bind("id", id).map(casebookRk.mapper()).findOne();
    }

    public Optional<Casebook> findCasebookByScriptId(String scriptId) {
        String sql = casebookRk.sql("select @columns from @records where script_id = :scriptId");
        return handle.get().createQuery(sql).bind("scriptId", scriptId).map(casebookRk.mapper()).findOne();
    }

    public List<Casebook> listCasebooks(int limit, long offset) {
        var query = """
                select @record
                from @table
                order by id desc
                limit :limit offset :offset
                """;
        var handle = this.handle.get();
        String sql = casebookRk.sql(query);
        return handle.createQuery(sql)
                .bind("limit", limit)
                .bind("offset", offset)
                .map(casebookRk.mapper())
                .list();

    }
}
