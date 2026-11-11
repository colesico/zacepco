package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.*;
import java.util.stream.Collectors;

public final class SceneSpec {

    @YamlComment(text = "Scene short name")
    public String name;

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
            "Location ID - unique within script",
            "ID format: L[A-Z0-9]{1,3}",
            ""
    })
    public Map<LocationSpec.Id, LocationSpec> locations = new LinkedHashMap<>();

    /**
     * Passages dictionary
     */
    @YamlComment(text = {
            "Ways between two locations",
            "Always connects only two locations, direction does not matter",
            "There can be only one direct way between two locations",
            "Way ID - unique within script",
            "ID format: W[A-Z0-9]{1,3}",
    })
    public Map<WaySpec.Id,WaySpec> ways = new LinkedHashMap<>();

    public Optional<LocationSpec> findLocationById(LocationSpec.Id id) {
        return locations.values().stream()
                .filter(loc -> loc.id.equals(id))
                .findFirst();
    }

    public Optional<LocationSpec> findLocationByPos(LocationPosSpec pos) {
        return locations.values().stream()
                .filter(loc -> loc.pos.equals(pos))
                .findFirst();
    }

    public List<WaySpec> findConnectionsByLocationId(LocationSpec.Id id) {
        return ways.values().stream()
                .filter(tr -> tr.locations.contains(id))
                .collect(Collectors.toList());
    }

}
