package colesico.zacepco.docket.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.model.Script;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScriptDao {
    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    /**
     * Case file Record Kit
     */
    private final ScriptRk scriptRk;

    public ScriptDao(Provider<Handle> handle, ScriptRk scriptRk) {
        this.handle = handle;
        this.scriptRk = scriptRk;
    }

    public Long getScriptId() {
        var sql = "UPDATE scripts_id_sequence SET value = value + 1 WHERE id = 1 RETURNING value";
        return handle.get().createQuery(sql).mapTo(Long.class).one();
    }

    public void createScript(Script script) {
        String sql = scriptRk.sql("insert into @table (@columns) values (@values)");
        handle.get().createUpdate(sql).bindMap(scriptRk.map(script)).execute();
    }

    public Optional<Script> findScriptById(Long id) {
        String sql = scriptRk.sql("select @columns from @table where id = :id");
        return handle.get().createQuery(sql).bind("id", id).map(scriptRk.mapper()).findOne();
    }

    public Optional<Script> findScript(String uid, Integer version) {
        String sql = scriptRk.sql("select @columns from @table where script_uid = :uid and script_version = :version");
        return handle.get().createQuery(sql)
                .bind("uid",uid)
                .bind("version", version)
                .map(scriptRk.mapper()).findOne();
    }

    public List<Script> listScripts(int limit, long offset) {
        var query = """
                select @record
                from @table
                where status in ('PUBLIC','RESTRICTED')
                order by id desc
                limit :limit offset :offset
                """;
        var handle = this.handle.get();
        String sql = scriptRk.sql(query);
        return handle.createQuery(sql)
                .bind("limit", limit)
                .bind("offset", offset)
                .map(scriptRk.mapper())
                .list();
    }
}
