package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Location> findLocationById(LocationId id) {
        return locations.stream()
                .filter(loc -> loc.getId().equals(id))
                .findFirst();
    }

    public Optional<Location> findLocationByPos(LocationPos pos) {
        return locations.stream()
                .filter(loc -> loc.getPos().equals(pos))
                .findFirst();
    }

    public List<Transition> findTransitionsByLocationId(LocationId id) {
        return transitions.stream()
                .filter(tr -> tr.getFrom().equals(id) || tr.getTo().equals(id))
                .collect(Collectors.toList());
    }

    public List<Transition> findTransitionsFromLocationId(LocationId id) {
        return transitions.stream()
                .filter(tr -> tr.getFrom().equals(id))
                .collect(Collectors.toList());
    }

    public List<Transition> findTransitionsToLocationId(LocationId id) {
        return transitions.stream()
                .filter(tr -> tr.getTo().equals(id))
                .collect(Collectors.toList());
    }

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
