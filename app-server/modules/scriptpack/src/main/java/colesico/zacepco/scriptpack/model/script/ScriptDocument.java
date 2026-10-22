package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

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
    public InvestigationSpec investigation;

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

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public InvestigationSpec getInvestigation() {
        return investigation;
    }

    public void setInvestigation(InvestigationSpec investigation) {
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
