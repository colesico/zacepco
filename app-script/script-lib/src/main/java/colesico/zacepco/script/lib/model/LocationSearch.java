package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Location search definition
 */
public class LocationSearch {
    /**
     * Location to be search
     */
    public Integer locationId;

    /**
     * Base search cost
     */
    public Integer baseCost;

    /**
     * Extra cost for thoroughness
     */
    public Integer detailedCost;

    /**
     * Artifact definitions
     */
    public List<LocationArtefact> artifacts;
}
