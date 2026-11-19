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
            "Crime phase specification",
            "Describes objective crime world,",
            "initial state for investigation",
            "and answer verification data",
            ""
    })
    public CrimeSpec crime = new CrimeSpec();

    @YamlComment(text = {
            "",
            "Investigation phase specification",
            "Describes investigation model and its params",
            ""
    })
    public InvestigationSpec investigation = new InvestigationSpec();

    @Override
    public String toString() {
        return "Script{" +
                "script=" + metainfo +
                '}';
    }
}
