package colesico.zacepco.script.lib.model;

/**
 * Script model
 */
public class Script {

    /**
     * Script data model version (1.0.0... etc)
     */
    public Integer model;

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
    public MetaInf metainf;

    /**
     * Miscellaneous definitions
     */
    public Miscellaneous miscellaneous;

    /**
     * Characters
     */
    public PersonageDictionary personages;

    /**
     * Locations
     */
    public LocationDictionary locations;

    /**
     * Artifacts
     */
    public ArtifactDictionary artifacts;

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
