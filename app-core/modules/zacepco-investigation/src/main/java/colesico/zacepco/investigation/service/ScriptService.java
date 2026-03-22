package colesico.zacepco.investigation.service;

import colesico.framework.ioc.production.Supplier;
import colesico.framework.service.Service;
import colesico.zacepco.investigation.model.ScriptRef;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;
import jakarta.inject.Provider;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ScriptService {

    private final Supplier<ScriptPackage> scriptPackageSupplier;

    public ScriptService(Supplier<ScriptPackage> scriptPackageSupplier) {
        this.scriptPackageSupplier = scriptPackageSupplier;
    }

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

    /**
     * Get Script package helper
     */
    public ScriptPackage pkg(Long id) {
        return scriptPackageSupplier.get(Paths.get(""));
    }
}
