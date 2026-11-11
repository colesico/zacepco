package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Script document
 */
public final class ScriptDocument {

    @YamlComment(text = {
            "",
            "Script meta information",
            ""
    })
    public MetainfoSpec metainfo = new MetainfoSpec();

    @YamlComment(text = {
            "",
            "Crime specification",
            "Describes entire crime environment (entities, state)",
            ""
    })
    public CrimeSpec crime = new CrimeSpec();

    @YamlComment(text = {
            "",
            "Investigation spec",
            "Describes relationships between crime entities for discovery",
            ""
    })
    public InvestigationSpec investigation = new InvestigationSpec();

    @YamlComment(text = {
            "",
            "General answers",
            ""
    })
    public AnswersSpec answers = new AnswersSpec();

    @Override
    public String toString() {
        return "Script{" +
                "script=" + metainfo +
                '}';
    }
}
