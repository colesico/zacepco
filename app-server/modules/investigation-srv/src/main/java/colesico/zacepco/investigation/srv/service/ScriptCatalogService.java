package colesico.zacepco.investigation.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.investigation.srv.dao.ScriptCatalogDao;
import colesico.zacepco.investigation.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.investigation.srv.model.ScriptEntry;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;
import jakarta.inject.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScriptCatalogService {

    private final ScriptCatalogDao scriptCatalogDao;

    /**
     * Script package manager
     */
    private final Supplier<ScriptPackage> scriptPackage;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public ScriptCatalogService(
            ScriptCatalogDao scriptCatalogDao,
            @Classed(StoragePackageDriver.class)
            Supplier<ScriptPackage> scriptPackage,
            Provider<Identity> identity) {

        this.scriptCatalogDao = scriptCatalogDao;
        this.scriptPackage = scriptPackage;
        this.identity = identity;
    }

    protected String packageId(Long entryId) {
        return "script" + entryId;
    }

    @RequireIdentity
    public ScriptEntry addScript(InputStream scriptPackageData) {
        Long userId = identity.get().longId();
        return addScript(userId, scriptPackageData);
    }

    /**
     * Add script package to catalog
     *
     * @return script reference
     */
    public ScriptEntry addScript(Long userId, InputStream scriptPackageData) {

        var scriptId = scriptCatalogDao.createScriptEntryId();

        var scriptPackage = this.scriptPackage.get(packageId(scriptId));

        Script script;
        try {
            scriptPackage.importFrom(scriptPackageData);
            script = scriptPackage.script().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ScriptEntry scriptEntry = new ScriptEntry();
        scriptEntry.setId(scriptId);
        scriptEntry.setUserId(userId);
        scriptEntry.setCreatedAt(new Date());

        scriptEntry.setUuid(script.meta.uuid);
        scriptEntry.setTitle(script.meta.title);
        scriptEntry.setAnnotation(script.meta.annotation);
        scriptEntry.setAuthors(script.meta.authors);
        scriptEntry.setVersion(script.meta.version);
        scriptEntry.setCreationDate(script.meta.creationDate);

        scriptCatalogDao.createScriptEntry(scriptEntry);

        return scriptEntry;
    }

    /**
     * Remove script entry from catalog
     */
    public void removeScript(Long entryId) {

    }

    /**
     * Get scrip entry by id
     */
    public Optional<ScriptEntry> findScriptById(Long id) {
        return scriptCatalogDao.findScriptEntryById(id);
    }

    /**
     * List script entries
     */
    public List<ScriptEntry> lastScripts(int limit, long offset) {
        return scriptCatalogDao.lastScriptEntries(limit, offset);
    }

    /**
     * Get Script package helper
     *
     * @param entryId script entry id
     */
    public ScriptPackage scriptPackage(Long entryId) {
        return scriptPackage.get(Paths.get(""));
    }
}
