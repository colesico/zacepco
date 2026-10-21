package colesico.zacepco.respack.model.assets;


import colesico.zacepco.respack.yaml.YamlComment;

import java.time.LocalDate;

/**
 * Assets general meta information
 */
public class AssetsMetainfo {

    /**
     * Assets schema version (<a href="https://semver.org/">SemVer</a>)
     */
    @YamlComment(text = {
            "Assets schema version (data model version)",
            "SimVer (semver.org) notation (i.e. 1.2.3)"
    })
    public String schema;

    /**
     * Unique Assets Name
     */
    @YamlComment(text = {
            "Unique assets name",
            "Consist of ASCII letters, numbers, underscore '_', max 32 chars"
    })
    public String uan;

    /**
     * Assets title
     */
    public String title;


    @YamlComment(text = "Assets description")
    public String description;

    /**
     * Assets authors
     */
    @YamlComment(text = "Assets authors")
    public String[] authors;

    /**
     * Assets license name i.e. CC BY 4.0
     */
    @YamlComment(text = "License under which the assets is distributed")
    public String licence;

    /**
     * Assets creation date
     */
    @YamlComment(text = "Assets creation date YYYY-MM-DD")
    public LocalDate creationDate;

    /**
     * Assets content version (1..N)
     */
    @YamlComment(text = "Assets version (1..N)")
    public Integer version;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUan() {
        return uan;
    }

    public void setUan(String uan) {
        this.uan = uan;
    }

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
