package colesico.zacepco.script.lib.model;

/**
 * Script model
 */
public class Script {

    /**
     * Script schema version (1.. etc)
     */
    public Integer schema;

    /**
     * Script id
     */
    public String id;

    /**
     * Script version (1..N)
     */
    public Integer version;

    /**
     * Meta information
     */
    public MetaInf meta;

    /**
     * Miscellaneous definitions
     */
    public Miscellaneous misc;

    /**
     * Characters
     */
    public PersonageDict personages;

    /**
     * Locations
     */
    public LocationDict locations;

    /**
     * Artifacts
     */
    public ArtifactDict artifacts;

    /**
     * Location search
     */
    public SearchSpec search;

    /**
     * Character interrogation
     */
    public InterrogationSpec interrogation;

    /**
     * Clue expertise
     */
    public ExpertiseSpec expertise;
}
