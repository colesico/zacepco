package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

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
            "Describes entire crime environment (entities, state)",
            ""
    })
    public CrimeSpec crime = new CrimeSpec();

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation spec",
            "Describes relationships between crime entities for discovery",
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
