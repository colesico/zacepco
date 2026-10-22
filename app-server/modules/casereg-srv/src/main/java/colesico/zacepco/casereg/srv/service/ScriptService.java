package colesico.zacepco.casereg.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.dao.ScriptEntryDao;
import colesico.zacepco.casereg.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.casereg.srv.model.ScriptEntry;
import colesico.zacepco.casereg.srv.model.ScriptEntryAccessType;
import colesico.zacepco.casereg.srv.model.ScriptSummary;
import colesico.zacepco.scriptpack.model.script.ScriptDocument;
import colesico.zacepco.scriptpack.pkg.*;
import jakarta.inject.Provider;

import java.io.IOException;
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
    private final Supplier<ScriptPackage> resourcePackage;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public ScriptService(
            ScriptEntryDao scriptEntryDao,
            @Classed(StoragePackageDriver.class) Supplier<ScriptPackage> resourcePackage,
            Provider<Identity> identity,
            ScriptAssetsService assetsService) {

        this.scriptEntryDao = scriptEntryDao;
        this.resourcePackage = resourcePackage;
        this.identity = identity;
        this.assetsService = assetsService;
    }

    protected String scriptPackageId(Long scriptEntryId) {
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

        var scriptEntryId = scriptEntryDao.createScriptEntryId();

        ScriptDocument script;

        try (var scriptPackage = resourcePackage(scriptEntryId);) {
            scriptPackage.importFrom(scriptPackageData);
            assetsService.createThumbs(scriptEntryId, scriptPackage);
            script = scriptPackage.script().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ScriptEntry scriptEntry = new ScriptEntry();
        scriptEntry.setId(scriptEntryId);
        scriptEntry.setUserId(userId);
        scriptEntry.setAccess(ScriptEntryAccessType.PRIVATE);
        scriptEntry.setCreatedAt(new Date());

        var summary = new ScriptSummary();
        scriptEntry.setSummary(summary);

        summary.setId(script.metainfo.id);
        summary.setTitle(script.metainfo.title);
        summary.setAnnotation(script.metainfo.annotation);
        summary.setAuthors(Arrays.asList(script.metainfo.authors));
        summary.setVersion(script.metainfo.version);
        summary.setCreationDate(script.metainfo.creationDate);

        scriptEntryDao.createScriptEntry(scriptEntry);

        return scriptEntry;
    }

    public void removeScriptEntry(Long id) {

    }

    public Optional<ScriptEntry> findScriptEntryById(Long id) {
        return scriptEntryDao.findScriptEntryById(id);
    }

    /**
     * Get script entry by {@link colesico.zacepco.scriptpack.model.script.ScriptMetainfo#id}
     */
    public Optional<ScriptEntry> findScriptEntryByUsn(String usn) {
        return scriptEntryDao.findScriptEntryByUsn(usn);
    }

    /**
     * List script entries in reverse creation order
     */
    public List<ScriptEntry> listScriptEntries(int limit, long offset) {
        return scriptEntryDao.listScriptEntries(limit, offset);
    }

    /**
     * Get Resource package API
     *
     * @param scriptEntryId script entry id
     */
    @PlainMethod
    public ScriptPackage resourcePackage(Long scriptEntryId) {
        return resourcePackage.get(scriptPackageId(scriptEntryId));
    }
}
