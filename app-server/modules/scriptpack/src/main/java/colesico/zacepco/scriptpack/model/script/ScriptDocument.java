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

    @YamlComment(text = {
            "",
            "Crime specification",
            "Describes entire crime environment",
            ""
    })
    public Crime crime;

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation spec",
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

    public Crime getSetting() {
        return crime;
    }

    public void setSetting(Crime crime) {
        this.crime = crime;
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
