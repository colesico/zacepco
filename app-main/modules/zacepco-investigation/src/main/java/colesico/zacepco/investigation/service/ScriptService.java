package colesico.zacepco.investigation.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.investigation.dao.ScriptDao;
import colesico.zacepco.investigation.filestorage.StoragePackageDriver;
import colesico.zacepco.investigation.model.ScriptRef;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class ScriptService {

    private final ScriptDao scriptDao;

    /**
     * Script package manager
     */
    private final Supplier<ScriptPackage> scriptPackage;

    public ScriptService(
            ScriptDao scriptDao,
            @Classed(StoragePackageDriver.class)
            Supplier<ScriptPackage> scriptPackage) {
        this.scriptDao = scriptDao;
        this.scriptPackage = scriptPackage;
    }

    protected String packageId(Long scriptId) {
        return "script" + scriptId;
    }

    /**
     * Load script package to db
     *
     * @return script reference id
     */
    public ScriptRef upload(InputStream is) {

        var scriptId = scriptDao.createScriptId();

        var scriptPackage = this.scriptPackage.get(packageId(scriptId));

        Script script;
        try {
            scriptPackage.importFrom(is);
            script = scriptPackage.script().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ScriptRef scriptRef = new ScriptRef();
        scriptRef.setId(scriptId);
        scriptRef.setUuid(script.meta.uuid);
        scriptRef.setTitle(script.meta.title);
        scriptRef.setAnnotation(script.meta.annotation);
        scriptRef.setCreated(script.meta.created);

        return scriptRef;
    }

    /**
     * Remove script from repository
     */
    public void remove(Long id) {

    }

    /**
     * Get scrip reference by id
     */
    public ScriptRef getRef(Long id) {
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
