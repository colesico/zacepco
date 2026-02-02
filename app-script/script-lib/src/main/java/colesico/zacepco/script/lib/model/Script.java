package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Script model
 */
public class Script {

    /**
     * Script model version (1.0.0... etc)
     */
    public String script;

    /**
     * Script common information
     */
    public MetaInf metainf;

    /**
     * Miscellaneous definitions
     */
    public Definitions definitions;

    /**
     * Game  characters
     */
    public List<Personage> personages;

    /**
     * Locations graph
     */
    public List<Location> locations;

    /**
     * Artifacts definitions
     */
    public List<Artifact> artifacts;


    public List<ArtifactParams> artifactParams;

    /**
     * Location search definitions
     */
    public LocationSearch search;
}
