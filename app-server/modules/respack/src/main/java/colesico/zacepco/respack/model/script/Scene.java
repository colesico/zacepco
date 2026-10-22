package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

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
            "Connections between two locations.",
            "Always connects only two locations, direction does not matter."
    })
    public List<Connection> connections;

    public Optional<Location> findLocationById(Location.Id id) {
        return locations.stream()
                .filter(loc -> loc.getId().equals(id))
                .findFirst();
    }

    public Optional<Location> findLocationByPos(LocationPos pos) {
        return locations.stream()
                .filter(loc -> loc.getPos().equals(pos))
                .findFirst();
    }

    public List<Connection> findConnectionsByLocationId(Location.Id id) {
        return connections.stream()
                .filter(tr -> tr.getLocations().contains(id))
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

    public List<Connection> getPassages() {
        return connections;
    }

    public void setPassages(List<Connection> connections) {
        this.connections = connections;
    }
}
