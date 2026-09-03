package colesico.zacepco.inquiry.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.inquiry.srv.model.ScriptRef;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScriptRefDao {
    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    /**
     * Script Record Kit
     */
    private final ScriptRefRK scriptRefRk;

    public ScriptRefDao(Provider<Handle> handle, ScriptRefRK scriptRefRk) {
        this.handle = handle;
        this.scriptRefRk = scriptRefRk;
    }

    public Long createScriptId() {
        var handle = this.handle.get();
        return handle.createQuery("select nextval('script_seq')").mapTo(Long.class).one();
    }

    public void createScriptRef(ScriptRef script) {
        var handle = this.handle.get();
        String sql = scriptRefRk.sql("insert into @record (@columns) values (@values)");
        handle.createUpdate(sql).bindMap(scriptRefRk.map(script)).execute();
    }

    public Optional<ScriptRef> findScriptRefById(Long scriptRefId) {
        var handle = this.handle.get();
        String sql = scriptRefRk.sql("select @columns from @records where id = :id");
        return handle.createQuery(sql).bind("id", scriptRefId).map(scriptRefRk.mapper()).findOne();
    }

    public Optional<ScriptRef> findScriptRefByUuid(String uuid) {
        var handle = this.handle.get();
        String sql = scriptRefRk.sql("select @columns from @records where uuid = :uuid");
        return handle.createQuery(sql).bind("uuid", uuid).map(scriptRefRk.mapper()).findOne();
    }

    public List<ScriptRef> lastScriptRefs(Long limit, Long offset) {
        var query = """
                select @record
                from @table
                order by created
                limit :limit offset :offset
                """;
        var handle = this.handle.get();
        String sql = scriptRefRk.sql(query);
        return handle.createQuery(sql)
                .bind("limit", limit)
                .bind("offset", offset)
                .map(scriptRefRk.mapper())
                .list();

    }
}
