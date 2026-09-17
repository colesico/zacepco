package colesico.zacepco.investigation.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.investigation.srv.dao.ScriptRefDao;
import colesico.zacepco.investigation.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.investigation.srv.model.ScriptRef;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScriptRefService {

    private final ScriptRefDao scriptRefDao;

    /**
     * Script package manager
     */
    private final Supplier<ScriptPackage> scriptPackage;

    public ScriptRefService(
            ScriptRefDao scriptRefDao,
            @Classed(StoragePackageDriver.class)
            Supplier<ScriptPackage> scriptPackage) {
        this.scriptRefDao = scriptRefDao;
        this.scriptPackage = scriptPackage;
    }

    protected String packageId(Long refId) {
        return "script" + refId;
    }

    /**
     * Load script package to db
     *
     * @return script reference
     */
    public ScriptRef createScriptRef(Long userId, InputStream scriptPackageData) {

        var scriptId = scriptRefDao.createScriptRefId();

        var scriptPackage = this.scriptPackage.get(packageId(scriptId));

        Script script;
        try {
            scriptPackage.importFrom(scriptPackageData);
            script = scriptPackage.script().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ScriptRef scriptRef = new ScriptRef();
        scriptRef.setId(scriptId);
        scriptRef.setUserId(userId);
        scriptRef.setCreatedAt(new Date());

        scriptRef.setUuid(script.meta.uuid);
        scriptRef.setTitle(script.meta.title);
        scriptRef.setAnnotation(script.meta.annotation);
        scriptRef.setAuthors(script.meta.authors);
        scriptRef.setVersion(script.meta.version);
        scriptRef.setCreationDate(script.meta.creationDate);

        scriptRefDao.createScriptRef(scriptRef);

        return scriptRef;
    }

    /**
     * Remove script reference from repository
     */
    public void removeScriptRef(Long refId) {

    }

    /**
     * Get scrip reference by id
     */
    public Optional<ScriptRef> findScriptRefById(Long id) {
        return scriptRefDao.findScriptRefById(id);
    }

    /**
     * List script references
     */
    public List<ScriptRef> lastScriptRefs(int limit, long offset) {
        return scriptRefDao.lastScriptRefs(limit, offset);
    }

    /**
     * Get Script package helper
     *
     * @param scriptRefId script reference id
     */
    public ScriptPackage scriptPackage(Long scriptRefId) {
        return scriptPackage.get(Paths.get(""));
    }
}
