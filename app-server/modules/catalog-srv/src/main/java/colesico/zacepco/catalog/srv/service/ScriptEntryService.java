package colesico.zacepco.catalog.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.catalog.srv.dao.ScriptEntryDao;
import colesico.zacepco.catalog.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.catalog.srv.model.ScriptAccessType;
import colesico.zacepco.catalog.srv.model.ScriptEntry;
import colesico.zacepco.catalog.srv.model.ScriptSummary;
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

    private final ScriptAssetsService assetsService;

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
            @Classed(StoragePackageDriver.class) Supplier<ScriptPackage> scriptPackage,
            Provider<Identity> identity,
            ScriptAssetsService assetsService) {

        this.scriptEntryDao = scriptEntryDao;
        this.scriptPackage = scriptPackage;
        this.identity = identity;
        this.assetsService = assetsService;
    }

    protected String scriptPackageId(Long scriptEntryId) {
        return "script/" + scriptEntryId;
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

        var scriptEntryId = scriptEntryDao.createScriptEntryId();

        Script script;

        try (var scriptPackage = scriptPackage(scriptEntryId);) {
            scriptPackage.importFrom(scriptPackageData);
            assetsService.createAssets(scriptEntryId, scriptPackage);
            script = scriptPackage.script().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ScriptEntry scriptEntry = new ScriptEntry();
        scriptEntry.setId(scriptEntryId);
        scriptEntry.setUserId(userId);
        scriptEntry.setAccess(ScriptAccessType.PRIVATE);
        scriptEntry.setCreatedAt(new Date());

        var summary = new ScriptSummary();
        scriptEntry.setSummary(summary);

        summary.setId(script.meta.id);
        summary.setTitle(script.meta.title);
        summary.setAnnotation(script.meta.annotation);
        summary.setAuthors(Arrays.asList(script.meta.authors));
        summary.setVersion(script.meta.version);
        summary.setCreationDate(script.meta.creationDate);

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
     * @param scriptEntryId script entry id
     */
    @PlainMethod
    public ScriptPackage scriptPackage(Long scriptEntryId) {
        return scriptPackage.get(scriptPackageId(scriptEntryId));
    }
}
