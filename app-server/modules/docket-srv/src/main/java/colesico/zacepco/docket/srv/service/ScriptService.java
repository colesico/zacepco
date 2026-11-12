package colesico.zacepco.docket.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.dao.ScriptEntryDao;
import colesico.zacepco.docket.srv.model.ScriptEntry;
import colesico.zacepco.docket.srv.script.FileStoragePackageDriver;
import colesico.zacepco.docket.srv.model.ScriptStatus;
import colesico.zacepco.docket.srv.model.ScriptSummary;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;
import colesico.zacepco.script.pkg.*;
import jakarta.inject.Provider;

import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class ScriptService {

    private final ScriptAssetsService assetsService;

    private final ScriptEntryDao scriptEntryDao;

    /**
     * Resource package API
     */
    private final Supplier<ScriptPackage> scriptPackage;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public ScriptService(
            ScriptEntryDao scriptEntryDao,
            @Classed(FileStoragePackageDriver.class) Supplier<ScriptPackage> scriptPackage,
            Provider<Identity> identity,
            ScriptAssetsService assetsService) {

        this.scriptEntryDao = scriptEntryDao;
        this.scriptPackage = scriptPackage;
        this.identity = identity;
        this.assetsService = assetsService;
    }

    protected String scriptPackageId(String scriptId) {
        return "script-" + scriptId.toUpperCase();
    }

    @RequireIdentity
    public ScriptEntry createScriptEntry(InputStream scriptPackageData) {
        Long userId = identity.get().longId();
        return createScriptEntry(userId, scriptPackageData);
    }

    /**
     * Add script package to registry
     */
    public ScriptEntry createScriptEntry(Long userId, InputStream scriptPackageData) {

        ScriptDocument script;

        try (var scriptPackage = scriptPackage(scriptEntryId)) {
            scriptPackage.importFrom(scriptPackageData);
            assetsService.scaleImages(scriptEntryId, scriptPackage);
            script = scriptPackage.script().read();
        }

        ScriptEntry scriptEntry = new ScriptEntry();
        scriptEntry.setId(script.metainfo.id);
        scriptEntry.setUserId(userId);
        scriptEntry.setStatus(ScriptStatus.PRIVATE);
        scriptEntry.setCreatedAt(new Date());

        var summary = new ScriptSummary();
        scriptEntry.setSummary(summary);

        summary.setUid(script.metainfo.id);
        summary.setVersion(script.metainfo.version);
        summary.setTitle(script.metainfo.title);
        summary.setAnnotation(script.metainfo.annotation);
        summary.setAuthors(Arrays.asList(script.metainfo.authors));
        summary.setCreationDate(script.metainfo.creationDate);

        scriptEntryDao.createScriptEntry(scriptEntry);

        return scriptEntry;
    }

    public void removeScriptEntry(Long id) {

    }

    public Optional<ScriptEntry> findScriptEntryById(String id) {
        return scriptEntryDao.findScriptEntryById(id);
    }

    /**
     * List script entries in reverse creation order
     */
    public List<ScriptEntry> listScriptEntries(int limit, long offset) {
        return scriptEntryDao.listScriptEntries(limit, offset);
    }

    /**
     * Get Script package API
     *
     * @param scriptId script entry id
     */
    @PlainMethod
    public ScriptPackage scriptPackage(String scriptId) {
        return scriptPackage.get(scriptPackageId(scriptId));
    }
}
