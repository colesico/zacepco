package colesico.zacepco.casereg.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.model.ScriptEntry;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScriptEntryDao {
    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    /**
     * Case file Record Kit
     */
    private final ScriptEntryRk scriptEntryRk;

    public ScriptEntryDao(Provider<Handle> handle, ScriptEntryRk scriptEntryRk) {
        this.handle = handle;
        this.scriptEntryRk = scriptEntryRk;
    }

    public Long createScriptEntryId() {
        var sql = "UPDATE scripts_id_sequence SET value = value + 1 WHERE id = 1 RETURNING value";
        return handle.get().createQuery(sql).mapTo(Long.class).one();
    }

    public void createScriptEntry(ScriptEntry scriptEntry) {
        String sql = scriptEntryRk.sql("insert into @table (@columns) values (@values)");
        handle.get().createUpdate(sql).bindMap(scriptEntryRk.map(scriptEntry)).execute();
    }

    public Optional<ScriptEntry> findScriptEntryById(Long id) {
        String sql = scriptEntryRk.sql("select @columns from @records where id = :id");
        return handle.get().createQuery(sql).bind("id", id).map(scriptEntryRk.mapper()).findOne();
    }

    public Optional<ScriptEntry> findScriptEntryByUsn(String usn) {
        String sql = scriptEntryRk.sql("select @columns from @records where script_usn = :usn");
        return handle.get().createQuery(sql).bind("usn", usn).map(scriptEntryRk.mapper()).findOne();
    }

    public List<ScriptEntry> listScriptEntries(int limit, long offset) {
        var query = """
                select @record
                from @table
                order by id desc
                limit :limit offset :offset
                """;
        var handle = this.handle.get();
        String sql = scriptEntryRk.sql(query);
        return  handle.createQuery(sql)
                .bind("limit", limit)
                .bind("offset", offset)
                .map(scriptEntryRk.mapper())
                .list();
    }
}
