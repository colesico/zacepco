package colesico.zacepco.cases.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.cases.srv.dao.CaseFileDao;
import colesico.zacepco.cases.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.cases.srv.model.CaseFile;
import colesico.zacepco.cases.srv.model.CaseFileAccessType;
import colesico.zacepco.cases.srv.model.ScriptReference;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;
import jakarta.inject.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class CaseFileService {

    private final ScriptAssetsService assetsService;

    private final CaseFileDao caseFileDao;

    /**
     * Script package API
     */
    private final Supplier<ScriptPackage> scriptPackage;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public CaseFileService(
            CaseFileDao caseFileDao,
            @Classed(StoragePackageDriver.class) Supplier<ScriptPackage> scriptPackage,
            Provider<Identity> identity,
            ScriptAssetsService assetsService) {

        this.caseFileDao = caseFileDao;
        this.scriptPackage = scriptPackage;
        this.identity = identity;
        this.assetsService = assetsService;
    }

    protected String scriptPackageId(Long scriptEntryId) {
        return "script-" + scriptEntryId;
    }

    @RequireIdentity
    public CaseFile createCaseFile(InputStream scriptPackageData) {
        Long userId = identity.get().longId();
        return createCaseFile(userId, scriptPackageData);
    }

    /**
     * Add script package to catalog
     */
    public CaseFile createCaseFile(Long userId, InputStream scriptPackageData) {

        var scriptEntryId = caseFileDao.createCaseFileId();

        Script script;

        try (var scriptPackage = scriptPackage(scriptEntryId);) {
            scriptPackage.importFrom(scriptPackageData);
            assetsService.createAssets(scriptEntryId, scriptPackage);
            script = scriptPackage.script().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CaseFile caseFile = new CaseFile();
        caseFile.setId(scriptEntryId);
        caseFile.setUserId(userId);
        caseFile.setAccess(CaseFileAccessType.PRIVATE);
        caseFile.setCreatedAt(new Date());

        var scriptRef = new ScriptReference();
        caseFile.setScript(scriptRef);

        scriptRef.setId(script.meta.id);
        scriptRef.setTitle(script.meta.title);
        scriptRef.setAnnotation(script.meta.annotation);
        scriptRef.setAuthors(Arrays.asList(script.meta.authors));
        scriptRef.setVersion(script.meta.version);
        scriptRef.setCreationDate(script.meta.creationDate);

        caseFileDao.createCaseFile(caseFile);

        return caseFile;
    }

    /**
     * Remove case file from catalog
     */
    public void removeCaseFile(Long id) {

    }

    /**
     * Get case file by id
     */
    public Optional<CaseFile> findCaseFileById(Long id) {
        return caseFileDao.findCaseFileById(id);
    }

    /**
     * List case fies in reverse creation order
     */
    public List<CaseFile> listCaseFiles(int limit, long offset) {
        return caseFileDao.listScriptFiles(limit, offset);
    }

    /**
     * Get Script package API
     *
     * @param caseFileId script entry id
     */
    @PlainMethod
    public ScriptPackage scriptPackage(Long caseFileId) {
        return scriptPackage.get(scriptPackageId(caseFileId));
    }
}
