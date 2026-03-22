package colesico.zacepco.investigation.service;

import colesico.framework.service.Service;
import colesico.zacepco.investigation.model.ScriptRef;
import colesico.zacepco.script.model.script.Script;

import java.io.InputStream;
import java.util.List;

@Service
public class ScriptService {

    /**
     * Import script package
     *
     * @return script reference id
     */
    public Long create(InputStream scriptPkg) {
        return null;
    }

    /**
     * Remove script reference
     */
    public void remove(Long id) {

    }

    /**
     * Get scrip reference by id
     */
    public ScriptRef get(Long id) {
        return null;
    }

    /**
     * List script references
     */
    public List<ScriptRef> list(long limit, long offset) {
        return null;
    }
}
