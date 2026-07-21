package colesico.zacepco.script.lib.model.script;

import colesico.zacepco.script.lib.model.setting.Setting;
import colesico.zacepco.script.lib.model.investigation.Investigation;
import colesico.zacepco.script.lib.yaml.YamlComment;

import java.util.List;

/**
 * Script document
 */
public class Script {

    /**
     * Meta information
     */
    @YamlComment(text = {
            "",
            "Script meta information.",
            ""
    })
    public ScriptMetadata meta;

    @YamlComment(text = {
            "",
            "Imported scripts",
            "",
    })
    public List<ScriptReference> include;

    /**
     * Environment spec
     */
    @YamlComment(text = {
            "",
            "Crime setting specification.",
            "Describes entire crime environment.",
            ""
    })
    public Setting setting;

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation specification.",
            ""
    })
    public Investigation investigation;

    /**
     * General answers
     */
    @YamlComment(text = {
            "",
            "General answers.",
            ""
    })
    public Answers answers;

    public ScriptMetadata getMeta() {
        return meta;
    }

    public void setMeta(ScriptMetadata meta) {
        this.meta = meta;
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

    public List<ScriptReference> getInclude() {
        return include;
    }

    public void setInclude(List<ScriptReference> include) {
        this.include = include;
    }

    @Override
    public String toString() {
        return "Script{" +
                "meta=" + meta +
                '}';
    }
}
