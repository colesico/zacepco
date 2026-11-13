package colesico.zacepco.docket.srv.service;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.security.Identity;
import colesico.framework.security.authorization.RequireIdentity;
import colesico.framework.service.LocalMethod;
import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.docket.srv.dao.ScriptDao;
import colesico.zacepco.docket.srv.model.Script;
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

    private final ScriptAssetService assetsService;

    private final ScriptDao scriptDao;

    /**
     * Resource package API
     */
    private final Supplier<ScriptPackage> scriptPackage;

    /**
     * Current user
     */
    private final Provider<Identity> identity;

    public ScriptService(
            ScriptDao scriptDao,
            @Classed(FileStoragePackageDriver.class) Supplier<ScriptPackage> scriptPackage,
            Provider<Identity> identity,
            ScriptAssetService assetsService) {

        this.scriptDao = scriptDao;
        this.scriptPackage = scriptPackage;
        this.identity = identity;
        this.assetsService = assetsService;
    }

    @LocalMethod
    public String scriptPackageId(Long scriptId) {
        return "script-" + scriptId;
    }

    @RequireIdentity
    public Script createScript(InputStream scriptPackageData) {
        Long userId = identity.get().longId();
        return createScript(userId, scriptPackageData);
    }

    /**
     * Add script package to registry
     */
    public Script createScript(Long userId, InputStream scriptPackageData) {

        var scriptId = scriptDao.getScriptId();

        ScriptDocument scriptDoc;
        var scriptPackage = scriptPackage(scriptId);
        var prgDriver = (FileStoragePackageDriver) scriptPackage.driver();

        try {
            scriptPackage.importFrom(scriptPackageData);
            assetsService.scaleImages(scriptId, scriptPackage);
            scriptDoc = scriptPackage.scriptDocument().read();
        } catch (Exception e) {
            prgDriver.removePackage();
            throw new RuntimeException("Script reading error", e);
        }

        Script script = new Script();
        script.setId(scriptId);
        script.setUserId(userId);
        script.setStatus(ScriptStatus.PUBLIC);
        script.setCreatedAt(new Date());

        var summary = new ScriptSummary();
        script.setSummary(summary);

        summary.setUid(scriptDoc.metainfo.uid);
        summary.setVersion(scriptDoc.metainfo.version);
        summary.setTitle(scriptDoc.metainfo.title);
        summary.setAuthors(Arrays.asList(scriptDoc.metainfo.authors));

        try {
            scriptDao.createScript(script);
        } catch (Exception e) {
            prgDriver.removePackage();
            throw new RuntimeException("Script registration error", e);
        }
        return script;
    }

    public void removeScript(Long id) {

    }

    public Optional<Script> findScriptById(Long id) {
        return scriptDao.findScriptById(id);
    }

    /**
     * List script entries in reverse creation order
     */
    public List<Script> listScripts(int limit, long offset) {
        return scriptDao.listScripts(limit, offset);
    }

    /**
     * Get Script package API
     *
     * @param scriptId script id
     */
    @PlainMethod
    public ScriptPackage scriptPackage(Long scriptId) {
        return scriptPackage.get(scriptPackageId(scriptId));
    }
}
