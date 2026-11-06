package colesico.zacepco.investigation.model;

public class ScriptRef {
    /**
     * Investigation script ref
     */
    public String id;
    public Integer version;

    @Stateless
    public String title;

    @Stateless
    public String annotation;
}
