package colesico.zacepco.script.model.script;

import colesico.zacepco.script.model.setting.Setting;
import colesico.zacepco.script.model.investigation.Investigation;
import colesico.zacepco.script.yaml.YamlComment;

/**
 * Script document
 */
public class Script {

    /**
     * Meta information
     */
    @YamlComment("Script meta information")
    public ScriptMetadata meta;

    /**
     * Environment spec
     */
    @YamlComment("Crime setting specification\n" +
            "Describes entire crime environment")
    public Setting setting;

    /**
     * Investigation spec
     */
    @YamlComment("Investigation specification")
    public Investigation investigation;

    /**
     * General answers
     */
    @YamlComment("General answers")
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

    @Override
    public String toString() {
        return "Script{" +
                "meta=" + meta +
                '}';
    }
}
