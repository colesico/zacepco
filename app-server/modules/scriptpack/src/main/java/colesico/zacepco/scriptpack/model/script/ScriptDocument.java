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
    public ScriptMetainfoSpec metainfo;

    @YamlComment(text = {
            "",
            "Crime specification",
            "Describes entire crime environment",
            ""
    })
    public CrimeSpec crime;

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation spec",
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
    public AnswersSpec answers;

    public ScriptDocument() {
    }

    public ScriptMetainfoSpec getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(ScriptMetainfoSpec metainfo) {
        this.metainfo = metainfo;
    }

    public CrimeSpec getSetting() {
        return crime;
    }

    public void setSetting(CrimeSpec crime) {
        this.crime = crime;
    }

    public InvestigationSpec getInvestigation() {
        return investigation;
    }

    public void setInvestigation(InvestigationSpec investigation) {
        this.investigation = investigation;
    }

    public AnswersSpec getAnswers() {
        return answers;
    }

    public void setAnswers(AnswersSpec answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Script{" +
                "meta=" + metainfo +
                '}';
    }
}
