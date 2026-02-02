package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Location search
 */
public class LocationSearch {
    /**
     * Location to be search
     */
    public Integer location;

    /**
     * Base search cost
     */
    public Integer cost;

    /**
     * Extra cost for thoroughness
     */
    public Integer detailedCost;

    /**
     * Artifact definitions
     */
    public List<LocationArtefact> artifacts;
}
