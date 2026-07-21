package colesico.zacepco.script.lib.model.setting;

import colesico.zacepco.script.lib.yaml.YamlComment;

import java.util.List;

public class Scene {

    /**
     * Scene size
     */
    @YamlComment(text = "Grid size for locations placement")
    public SceneSize size;

    /**
     * Locations dictionary
     */
    @YamlComment(text = "Locations dictionary")
    public List<Location> locations;

    /**
     * Passages dictionary
     */
    @YamlComment(text = {
            "Transition between two locations. ",
            "Transition always connects only two locations, direction does not matter."
    })
    public List<Transition> transitions;

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

    public List<Transition> getPassages() {
        return transitions;
    }

    public void setPassages(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
