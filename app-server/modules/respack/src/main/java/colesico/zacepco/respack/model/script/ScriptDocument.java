package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;
import java.util.Optional;

/**
 * Script document
 */
public class ScriptDocument {

    /**
     * Meta information
     */
    @YamlComment(text = {
            "",
            "Script meta information",
            ""
    })
    public ScriptMetainfo metainfo;

    @YamlComment(text = {
            "",
            "Assets id to use with this script.",
            "Every script is strictly tied to one asset pack.",
            "Different scripts can utilize the same asset packs,",
            "but a single script cannot combine multiple asset packs",
            "",
    })
    public String assets;

    /**
     * Environment spec
     */
    @YamlComment(text = {
            "",
            "Crime setting specification",
            "Describes entire crime environment",
            ""
    })
    public Setting setting;

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation specification",
            ""
    })
    public Investigation investigation;

    /**
     * General answers
     */
    @YamlComment(text = {
            "",
            "General answers",
            ""
    })
    public Answers answers;

    public ScriptDocument() {
    }

    public ScriptMetainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(ScriptMetainfo metainfo) {
        this.metainfo = metainfo;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public Investigation getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Investigation investigation) {
        this.investigation = investigation;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Script{" +
                "meta=" + metainfo +
                '}';
    }
}
