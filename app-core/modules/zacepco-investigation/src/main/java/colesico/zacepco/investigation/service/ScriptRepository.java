package colesico.zacepco.investigation.service;

import colesico.framework.ioc.production.Supplier;
import colesico.framework.service.Service;
import colesico.zacepco.investigation.model.ScriptRef;
import colesico.zacepco.script.pkg.*;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ScriptRepository {

    private final Supplier<ScriptPackage> scriptPackage;

    public ScriptRepository(Supplier<ScriptPackage> scriptPackage) {
        this.scriptPackage = scriptPackage;
    }

    /**
     * Import script package to repository
     *
     * @return script reference id
     */
    public ScriptRef importFrom(InputStream is) {
        var scriptPackage = this.scriptPackage.get();
        return null;
    }

    /**
     * Remove from repository
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

    /**
     * Get Script package helper
     */
    public ScriptPackage pkg(Long id) {
        return scriptPackage.get(Paths.get(""));
    }
}
