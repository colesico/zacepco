package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SceneSpec {

    /**
     * Scene size
     */
    @YamlComment(text = "Grid size for locations placement", inline = false)
    public SceneSizeSpec size;

    /**
     * Locations dictionary
     */
    @YamlComment(text = {
            "",
            "Locations dictionary",
            ""
    })
    public List<LocationSpec> locations;

    /**
     * Passages dictionary
     */
    @YamlComment(text = {
            "Ways between two locations.",
            "Always connects only two locations, direction does not matter.",
            "There can be only one direct way between two locations"
    })
    public List<WaySpec> ways;

    public Optional<LocationSpec> findLocationById(LocationSpec.Id id) {
        return locations.stream()
                .filter(loc -> loc.getId().equals(id))
                .findFirst();
    }

    public Optional<LocationSpec> findLocationByPos(LocationPosSpec pos) {
        return locations.stream()
                .filter(loc -> loc.getPos().equals(pos))
                .findFirst();
    }

    public List<WaySpec> findConnectionsByLocationId(LocationSpec.Id id) {
        return ways.stream()
                .filter(tr -> tr.getLocations().contains(id))
                .collect(Collectors.toList());
    }

    public SceneSizeSpec getSize() {
        return size;
    }

    public void setSize(SceneSizeSpec size) {
        this.size = size;
    }

    public List<LocationSpec> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationSpec> locations) {
        this.locations = locations;
    }

    public List<WaySpec> getPassages() {
        return ways;
    }

    public void setPassages(List<WaySpec> ways) {
        this.ways = ways;
    }
}
