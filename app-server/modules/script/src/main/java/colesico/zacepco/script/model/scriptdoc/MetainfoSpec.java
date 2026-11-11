package colesico.zacepco.script.model.scriptdoc;


import colesico.zacepco.script.yaml.YamlComment;

import java.time.LocalDate;

/**
 * Script meta information
 */
public final class MetainfoSpec {

    @YamlComment(text = {
            "Script schema version (data model version)",
            "SimVer (semver.org) notation (i.e. 1.2.3)"
    })
    public String schema = "1.0.0";

    @YamlComment(text = {
            "Global unique script identifier",
            "Can only consist of ASCII uppercase letters, numbers, underscore '_'"
    })
    public String id;

    public Integer version = 1;

    @YamlComment(text = "Script title")
    public String title = null;

    /**
     * Crime brief description
     */
    @YamlComment(text = "A brief crime summary without spoilers")
    public String annotation = null;

    /**
     * Script authors
     */
    @YamlComment(text = {"Authors list"})
    public String[] authors = new String[]{};

    /**
     * Script licence name i.e. CC BY 4.0
     */
    @YamlComment(text = {"License under which the script is distributed"})
    public String licence = "CC BY 4.0";

    /**
     * Script creation date
     */
    @YamlComment(text = "Script creation date YYYY-MM-DD")
    public LocalDate creationDate;

    /**
     * .
     */
    @YamlComment(text = {
            "Game estimated difficulty level",
            "Dimensionless coefficient [0..1]",
            "0 - easy; 1 - highly difficult"
    })
    public Double difficulty = 0.0;

    @YamlComment(text = "Estimated playtime, minutes", inline = false)
    public IntInterval estimatedTime = new IntInterval(0, 0);

    @YamlComment(text = "Recommended number of players (min-max)", inline = false)
    public IntInterval playersNum = new IntInterval(1, 10);

    @YamlComment(text = {
            "Recommended age for players",
            "Example: 18+ or 4-10"
    })
    public String age = "18+";

    @Override
    public String toString() {
        return "ScriptMetadata{" +
                "schema='" + schema + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
