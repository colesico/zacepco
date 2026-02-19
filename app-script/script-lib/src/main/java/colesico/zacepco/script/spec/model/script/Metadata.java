package colesico.zacepco.script.spec.model.script;


import java.time.LocalDate;

/**
 * Script general info
 */
public class Metadata {

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
     * Script content version (1..N)
     */
    public Integer version;

    /**
     * Estimated difficulty level
     */
    public Double difficulty;

    /**
     * Estimated  time to play in minutes  (min-max)
     */
    public IntInterval estimatedTime;

    /**
     * Recommended players number  (min-max)
     */
    public IntInterval playersNum;

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
}
