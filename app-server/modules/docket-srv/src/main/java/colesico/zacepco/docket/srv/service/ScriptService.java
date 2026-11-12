package colesico.zacepco.docket.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.LocalMethod;
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

    @LocalMethod
    public String scriptPackageId(Long scriptEntryId) {
        return "script-" + scriptEntryId;
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

        var scriptEntryId = scriptEntryDao.getScriptEntryId();

        ScriptDocument script;

        try (var scriptPackage = scriptPackage(scriptEntryId)) {
            scriptPackage.importFrom(scriptPackageData);
            assetsService.scaleImages(scriptEntryId, scriptPackage);
            script = scriptPackage.script().read();
        }

        ScriptEntry entry = new ScriptEntry();
        entry.setId(scriptEntryId);
        entry.setUserId(userId);
        entry.setStatus(ScriptStatus.PUBLIC);
        entry.setCreatedAt(new Date());

        var summary = new ScriptSummary();
        entry.setSummary(summary);

        summary.setUid(script.metainfo.uid);
        summary.setVersion(script.metainfo.version);
        summary.setTitle(script.metainfo.title);
        summary.setAuthors(Arrays.asList(script.metainfo.authors));

        try {
            scriptEntryDao.createScriptEntry(entry);
        } catch (Exception e) {
            try (var scriptPackage = scriptPackage(scriptEntryId)) {
                FileStoragePackageDriver prgDriver = (FileStoragePackageDriver) scriptPackage.driver();
                prgDriver.removePackage();
            }
        }
        return entry;
    }

    public void removeScriptEntry(Long id) {

    }

    public Optional<ScriptEntry> findScriptEntryById(Long id) {
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
     * @param scriptEntryId script entry id
     */
    @PlainMethod
    public ScriptPackage scriptPackage(Long scriptEntryId) {
        return scriptPackage.get(scriptPackageId(scriptEntryId));
    }
}
