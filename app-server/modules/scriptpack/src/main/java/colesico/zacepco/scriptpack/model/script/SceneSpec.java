package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SceneSpec {

    /**
     * Scene size
     */
    @YamlComment(text = "Grid size for locations placement", inline = false)
    public SceneSizeSpec size = new SceneSizeSpec();

    /**
     * Locations dictionary
     */
    @YamlComment(text = {
            "",
            "Locations dictionary",
            ""
    })
    public List<LocationSpec> locations = new ArrayList<>();

    /**
     * Passages dictionary
     */
    @YamlComment(text = {
            "Ways between two locations.",
            "Always connects only two locations, direction does not matter.",
            "There can be only one direct way between two locations"
    })
    public List<WaySpec> ways = new ArrayList<>();

    public Optional<LocationSpec> findLocationById(LocationSpec.Id id) {
        return locations.stream()
                .filter(loc -> loc.id.equals(id))
                .findFirst();
    }

    public Optional<LocationSpec> findLocationByPos(LocationPosSpec pos) {
        return locations.stream()
                .filter(loc -> loc.pos.equals(pos))
                .findFirst();
    }

    public List<WaySpec> findConnectionsByLocationId(LocationSpec.Id id) {
        return ways.stream()
                .filter(tr -> tr.getLocations().contains(id))
                .collect(Collectors.toList());
    }

}
