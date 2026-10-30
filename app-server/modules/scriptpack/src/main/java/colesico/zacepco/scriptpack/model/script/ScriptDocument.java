package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

/**
 * Script document
 */
public final class ScriptDocument {

    /**
     * Meta information
     */
    @YamlComment(text = {
            "",
            "Script meta information",
            ""
    })
    public ScriptMetainfoSpec metainfo = new ScriptMetainfoSpec();

    @YamlComment(text = {
            "",
            "Crime specification",
            "Describes entire crime environment",
            ""
    })
    public CrimeSpec crime = new CrimeSpec();

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation spec",
            ""
    })
    public InvestigationSpec investigation = new InvestigationSpec();

    /**
     * General answers
     */
    @YamlComment(text = {
            "",
            "General answers",
            ""
    })
    public AnswersSpec answers = new AnswersSpec();

    @Override
    public String toString() {
        return "Script{" +
                "meta=" + metainfo +
                '}';
    }
}
