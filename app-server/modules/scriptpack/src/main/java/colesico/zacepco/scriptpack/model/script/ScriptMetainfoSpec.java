package colesico.zacepco.scriptpack.model.script;


import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.time.LocalDate;

/**
 * Script general meta information
 */
public final class ScriptMetainfoSpec {

    /**
     * Script schema version (<a href="https://semver.org/">SemVer</a>)
     */
    @YamlComment(text = {
            "Script schema version (data model version)",
            "SimVer (semver.org) notation (i.e. 1.2.3)"
    })
    public String schema = "0.0.0";

    /**
     * Unique Script Identifier
     */
    @YamlComment(text = {
            "Unique script id",
            "Can only consist of ASCII letters, numbers, underscore '_'"
    })
    public String id;

    /**
     * Script title
     */
    public String title = "Title";

    /**
     * Crime brief description
     */
    @YamlComment(text = "A brief crime summary without spoilers")
    public String annotation;

    /**
     * Script authors
     */
    @YamlComment(text = {"Script authors"})
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
     * Script content version (1..N)
     */
    @YamlComment(text = "Script version (1..N)")
    public Integer version = 1;

    /**
     * Estimated difficulty level.
     */
    @YamlComment(text = {

            "Game difficulty",
            "Dimensionless coefficient [0..1].",
            "0 - easy; 1 - highly difficult."
    })
    public Double difficulty = 0.0;

    /**
     * Estimated  time to play in minutes  (min-max)
     */
    @YamlComment(text = "Estimated playtime, minutes", inline = false)
    public IntInterval estimatedTime = new IntInterval(0, 0);

    /**
     * Recommended players number  (min-max)
     */
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
