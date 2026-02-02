package colesico.zacepco.script.lib.model;

/**
 * Game character
 */
public class Personage {
    public Integer id;

    /**
     * Easy to remember id (First letter of name, etc)
     */
    public String key;

    /**
     * Personage name
     */
    public String name;

    /**
     * Brief personage description
     */
    public String description;

    /**
     * Hidden personage or not
     */
    public Boolean hidden;
}
