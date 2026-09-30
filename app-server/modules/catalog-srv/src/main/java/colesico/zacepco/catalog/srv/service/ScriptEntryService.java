package colesico.zacepco.catalog.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.catalog.srv.dao.ScriptEntryDao;
import colesico.zacepco.catalog.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.catalog.srv.model.ScriptAccessType;
import colesico.zacepco.catalog.srv.model.ScriptEntry;
import colesico.zacepco.catalog.srv.model.ScriptInfo;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;
import jakarta.inject.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
public class ScriptEntryService {

    private final ScriptEntryDao scriptEntryDao;

    /**
     * Script package manager
     */
    private final Supplier<ScriptPackage> scriptPackage;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public ScriptEntryService(
            ScriptEntryDao scriptEntryDao,
            @Classed(StoragePackageDriver.class)
            Supplier<ScriptPackage> scriptPackage,
            Provider<Identity> identity) {

        this.scriptEntryDao = scriptEntryDao;
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

        var scriptId = scriptEntryDao.createScriptEntryId();

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
        scriptEntry.setAccess(ScriptAccessType.PRIVATE);
        scriptEntry.setCreatedAt(new Date());

        var scriptInfo = new ScriptInfo();
        scriptEntry.setScriptInfo(scriptInfo);

        scriptInfo.setId(script.meta.id);
        scriptInfo.setTitle(script.meta.title);
        scriptInfo.setAnnotation(script.meta.annotation);
        scriptInfo.setAuthors(Arrays.asList(script.meta.authors));
        scriptInfo.setVersion(script.meta.version);
        scriptInfo.setCreationDate(script.meta.creationDate);

        scriptEntryDao.createScriptEntry(scriptEntry);

        return scriptEntry;
    }

    /**
     * Remove script entry from catalog
     */
    public void removeScriptEntry(Long id) {

    }

    /**
     * Get scrip entry by id
     */
    public Optional<ScriptEntry> findScriptEntryById(Long id) {
        return scriptEntryDao.findScriptEntryById(id);
    }

    /**
     * List script entries  in reverse creation order
     */
    public List<ScriptEntry> listScriptEntries(int limit, long offset) {
        return scriptEntryDao.listScriptEntries(limit, offset);
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
