package colesico.zacepco.script.lib.model.entity;

import java.util.List;

public class Scene {

    /**
     * Scene grid size
     */
    public Grid dim;

    public List<Location> locations;

    public List<Passage> passages;

    public Grid getDim() {
        return dim;
    }

    public void setDim(Grid dim) {
        this.dim = dim;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }
}
