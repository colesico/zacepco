package colesico.zacepco.cases.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.cases.srv.dao.CasebookDao;
import colesico.zacepco.cases.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.cases.srv.model.Casebook;
import colesico.zacepco.cases.srv.model.CasebookAccessType;
import colesico.zacepco.cases.srv.model.ScriptSummary;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;
import jakarta.inject.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class CasebookService {

    private final ScriptAssetsService assetsService;

    private final CasebookDao casebookDao;

    /**
     * Script package API
     */
    private final Supplier<ScriptPackage> scriptPackage;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public CasebookService(
            CasebookDao casebookDao,
            @Classed(StoragePackageDriver.class) Supplier<ScriptPackage> scriptPackage,
            Provider<Identity> identity,
            ScriptAssetsService assetsService) {

        this.casebookDao = casebookDao;
        this.scriptPackage = scriptPackage;
        this.identity = identity;
        this.assetsService = assetsService;
    }

    protected String scriptPackageId(Long scriptEntryId) {
        return "script-" + scriptEntryId;
    }

    @RequireIdentity
    public Casebook createCasebook(InputStream scriptPackageData) {
        Long userId = identity.get().longId();
        return createCasebook(userId, scriptPackageData);
    }

    /**
     * Add script package to registry
     */
    public Casebook createCasebook(Long userId, InputStream scriptPackageData) {

        var scriptEntryId = casebookDao.createCasebookId();

        Script script;

        try (var scriptPackage = scriptPackage(scriptEntryId);) {
            scriptPackage.importFrom(scriptPackageData);
            assetsService.createAssets(scriptEntryId, scriptPackage);
            script = scriptPackage.script().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Casebook casebook = new Casebook();
        casebook.setId(scriptEntryId);
        casebook.setUserId(userId);
        casebook.setAccess(CasebookAccessType.PRIVATE);
        casebook.setCreatedAt(new Date());

        var summary = new ScriptSummary();
        casebook.setSummary(summary);

        summary.setId(script.meta.id);
        summary.setTitle(script.meta.title);
        summary.setAnnotation(script.meta.annotation);
        summary.setAuthors(Arrays.asList(script.meta.authors));
        summary.setVersion(script.meta.version);
        summary.setCreationDate(script.meta.creationDate);

        casebookDao.createCasebook(casebook);

        return casebook;
    }

    /**
     * Remove case file from catalog
     */
    public void removeCasebook(Long id) {

    }

    /**
     * Get case file by id
     */
    public Optional<Casebook> findCasebookById(Long id) {
        return casebookDao.findCasebookById(id);
    }

    /**
     * List case fies in reverse creation order
     */
    public List<Casebook> listCasebooks(int limit, long offset) {
        return casebookDao.listCasebooks(limit, offset);
    }

    /**
     * Get Script package API
     *
     * @param casebookId script entry id
     */
    @PlainMethod
    public ScriptPackage scriptPackage(Long casebookId) {
        return scriptPackage.get(scriptPackageId(casebookId));
    }
}
