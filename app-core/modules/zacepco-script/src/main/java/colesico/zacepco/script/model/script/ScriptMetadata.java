package colesico.zacepco.script.model.script;


import colesico.zacepco.script.yaml.YamlComment;

import java.time.LocalDate;

/**
 * Script general metadata
 */
public class ScriptMetadata {

    /**
     * Script schema version (<a href="https://semver.org/">SemVer</a>)
     */
    @YamlComment("Script schema version (data model version)")
    public String schema;

    /**
     * Universally Unique Identifier
     */
    @YamlComment("Universally unique identifier (UUID) of the script")
    public String uuid;


    /**
     * Script title
     */
    public String title;

    /**
     * Crime brief description
     */
    @YamlComment("A brief script summary without spoilers")
    public String description;

    /**
     * Script authors
     */
    public String[] authors;

    /**
     * Script licence name i.e. CC BY 4.0
     */
    @YamlComment("License under which the script is distributed")
    public String licence;

    /**
     * Script creation date
     */
    @YamlComment("Script creation date YYYY-MM-DD")
    public LocalDate created;

    /**
     * Script content version (1..N)
     */
    @YamlComment("Script version")
    public Integer version;

    /**
     * Estimated difficulty level
     */
    @YamlComment("Game difficulty\nDimensionless positive coefficient")
    public Double difficulty;

    /**
     * Estimated  time to play in minutes  (min-max)
     */
    @YamlComment("Estimated playtime, minutes")
    public IntInterval estimatedTime;

    /**
     * Recommended players number  (min-max)
     */
    @YamlComment("Recommended number of players (min-max)")
    public IntInterval playersNum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public IntInterval getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(IntInterval estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public IntInterval getPlayersNum() {
        return playersNum;
    }

    public void setPlayersNum(IntInterval playersNum) {
        this.playersNum = playersNum;
    }

    @Override
    public String toString() {
        return "ScriptMetadata{" +
                "schema='" + schema + '\'' +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
