package colesico.zacepco.script.lib.model;

public class Metadata {

    /**
     * Script id
     */
    public String id;

    /**
     * Script authors
     */
    public String[] authors;

    public String title;

    public String description;

    /**
     * Script licence name i.e. CC BY 4.0
     */
    public String licence;

    /**
     * Estimated difficulty level
     */
    public Double difficulty;

    /**
     * Crime duration in ticks
     */
    public Integer duration;

    /**
     * Investigation budget (resource)
     */
    public Integer budget;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
