package colesico.zacepco.casework.srv.model;

import colesico.zacepco.respack.model.script.Location;

/**
 * Location state
 */

public class LocationState {

    private Location.Id locationId;

    /**
     * Location is discovered.
     * For hidden location that not discovered yet = false
     */
    private Boolean discovered;

    /**
     * Clues that not discovered yet for this location
     */
    private Integer cluesToDiscover;
}
