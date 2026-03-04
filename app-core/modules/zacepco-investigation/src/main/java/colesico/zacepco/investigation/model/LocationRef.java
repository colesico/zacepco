package colesico.zacepco.investigation.model;

import colesico.zacepco.script.model.entity.LocationId;

/**
 * Location reference
 */
public class LocationRef {

    private Long id;

    private Long investigationId;

    private LocationId locationId;

    /**
     * Location name
     */
    private String name;

    /**
     * Location is discovered.
     * For hidden location that not discovered yet = false
     */
    private Boolean discovered;

    /**
     * No clues to open
     */
    private Boolean exhausted;
}
