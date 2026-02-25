package colesico.zacepco.script.model.entity;

import java.util.List;

public class Scene {

    /**
     * Scene size
     */
    public SceneSize size;

    /**
     * Locations dictionary
     */
    public List<Location> locations;

    /**
     * Passages dictionary
     */
    public List<Passage> passages;

    public SceneSize getSize() {
        return size;
    }

    public void setSize(SceneSize size) {
        this.size = size;
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
