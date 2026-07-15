package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;

public class Scene {

    /**
     * Scene size
     */
    @YamlComment("Scene grid size for locations placement")
    public SceneSize size;

    /**
     * Locations dictionary
     */
    @YamlComment("Scene locations dictionary")
    public List<Location> locations;

    /**
     * Passages dictionary
     */
    @YamlComment("Passages between locations")
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
