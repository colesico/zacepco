package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Crime scene
 */
public class Scene {

    /**
     * Crime cene dimensions
     */
    public SceneDimensions dim;

    /**
     * Locations graph definition
     */
    public List<Location> locations;

    public SceneDimensions getDim() {
        return dim;
    }

    public void setDim(SceneDimensions dim) {
        this.dim = dim;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
