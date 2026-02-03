package colesico.zacepco.script.lib.model;


import java.time.LocalDate;

/**
 * Script general info
 */
public class Metadata {
    /**
     * Document schema version (1.. etc)
     */
    public Integer schema;

    /**
     * Document id
     */
    public String id;

    /**
     * Document version (1..N)
     */
    public Integer version;

    /**
     * Script name
     */
    public String name;

    /**
     * Crime brief description
     */
    public String description;

    /**
     * Script authors
     */
    public String[] authors;

    /**
     * Script licence name i.e. CC BY 4.0
     */
    public String licence;

    /**
     * Script creation date
     */
    public LocalDate created;

    /**
     * Estimated difficulty level
     */
    public Double difficulty;

    /**
     * Estimated time to play (from-to) in minutes
     */
    public Integer[] estimatedTime;

    /**
     * Recommended players number  (from-to)
     */
    public Integer[] playersNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public Integer[] getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer[] estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Integer[] getPlayersNum() {
        return playersNum;
    }

    public void setPlayersNum(Integer[] playersNum) {
        this.playersNum = playersNum;
    }
}
