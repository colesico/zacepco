package colesico.zacepco.investigation.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.investigation.srv.model.ScriptRef;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

@Service
@Transactional
public class ScriptDao {
    /**
     * Jdbi Handle
     */
    private final Provider<Handle> handle;

    /**
     * Script Record Kit
     */
    private final ScriptRK recordKit;

    public ScriptDao(Provider<Handle> handle, ScriptRK recordKit) {
        this.handle = handle;
        this.recordKit = recordKit;
    }

    public Long createScriptId() {
        var handle = this.handle.get();
        return handle.createQuery("select nextval('script_seq')").mapTo(Long.class).one();
    }

    public void createScript(ScriptRef script) {
        var handle = this.handle.get();
        String sql = recordKit.sql("insert into @record (@columns) values (@values)");
        handle.createUpdate(sql).bindMap(recordKit.map(script)).execute();
    }

    public ScriptRef readScript(Long id) {
        var handle = this.handle.get();
        String sql = recordKit.sql("select @columns from @records where id = :id");
        return handle.createQuery(sql).bind("id", id).map(recordKit.mapper()).one();
    }
}
