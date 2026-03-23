package colesico.zacepco.investigation.model;

import colesico.framework.jdbirec.Column;
import colesico.zacepco.script.model.script.IntInterval;

import java.time.LocalDate;

public class ScriptMetaRef {

    /**
     * Script title
     */
    @Column
    public String title;

    /**
     * Crime brief description
     */
    @Column
    public String description;

    /**
     * Script authors
     */

    public String[] authors;

    /**
     * Script licence name i.e. CC BY 4.0
     */
    @Column
    public String licence;

    /**
     * Script creation date
     */
    @Column
    public LocalDate created;

    /**
     * Script content version (1..N)
     */
    @Column
    public Integer version;

    /**
     * Estimated difficulty level
     */
    @Column
    public Double difficulty;

    /**
     * Estimated  time to play in minutes  (min-max)
     */
    @Column
    public IntInterval estimatedTime;

    /**
     * Recommended players number  (min-max)
     */
    @Column
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
}
