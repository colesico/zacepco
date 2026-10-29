package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Scene {

    /**
     * Scene size
     */
    @YamlComment(text = "Grid size for locations placement", inline = false)
    public SceneSize size;

    /**
     * Locations dictionary
     */
    @YamlComment(text = {
            "",
            "Locations dictionary",
            ""
    })
    public List<Location> locations;

    /**
     * Passages dictionary
     */
    @YamlComment(text = {
            "Ways between two locations.",
            "Always connects only two locations, direction does not matter.",
            "There can be only one direct way between two locations"
    })
    public List<Way> ways;

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

    public List<Way> findConnectionsByLocationId(Location.Id id) {
        return ways.stream()
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

    public List<Way> getPassages() {
        return ways;
    }

    public void setPassages(List<Way> ways) {
        this.ways = ways;
    }
}
