package colesico.zacepco.catalog.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.catalog.srv.model.ScriptEntry;
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
     * Script Record Kit
     */
    private final ScriptEntryRK scriptEntryRk;

    public ScriptEntryDao(Provider<Handle> handle, ScriptEntryRK scriptEntryRk) {
        this.handle = handle;
        this.scriptEntryRk = scriptEntryRk;
    }

    public Long createScriptEntryId() {
        var handle = this.handle.get();
        return handle.createQuery("select nextval('scripts_id_seq')").mapTo(Long.class).one();
    }

    public void createScriptEntry(ScriptEntry script) {
        var handle = this.handle.get();
        String sql = scriptEntryRk.sql("insert into @record (@columns) values (@values)");
        handle.createUpdate(sql).bindMap(scriptEntryRk.map(script)).execute();
    }

    public Optional<ScriptEntry> findScriptEntryById(Long id) {
        var handle = this.handle.get();
        String sql = scriptEntryRk.sql("select @columns from @records where id = :id");
        return handle.createQuery(sql).bind("id", id).map(scriptEntryRk.mapper()).findOne();
    }

    public Optional<ScriptEntry> findScriptEntryBySid(String sid) {
        var handle = this.handle.get();
        String sql = scriptEntryRk.sql("select @columns from @records where uuid = :uuid");
        return handle.createQuery(sql).bind("sid", sid).map(scriptEntryRk.mapper()).findOne();
    }

    public List<ScriptEntry> listScriptEntries(int limit, long offset) {
        var query = """
                select @record
                from @table
                order by created
                limit :limit offset :offset
                """;
        var handle = this.handle.get();
        String sql = scriptEntryRk.sql(query);
        return handle.createQuery(sql)
                .bind("limit", limit)
                .bind("offset", offset)
                .map(scriptEntryRk.mapper())
                .list();

    }
}
