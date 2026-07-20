package colesico.zacepco.investigation.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.investigation.model.ScriptRef;
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
     * User Record Kit
     */
    private final ScriptRK scriptRk;

    public ScriptDao(Provider<Handle> handle, ScriptRK scriptRk) {
        this.handle = handle;
        this.scriptRk = scriptRk;
    }

    public Long createScriptId() {
        var handle = this.handle.get();
        return handle.createQuery("SELECT nextval('script_seq')").mapTo(Long.class).one();
    }

    public void createScript(ScriptRef script) {
        var handle = this.handle.get();
        String query = scriptRk.sql("insert into @scripts (@columns) values (@values)");
        handle.createUpdate(query).bindMap(scriptRk.map(script)).execute();
    }
}
