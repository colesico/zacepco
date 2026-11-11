package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

public class LocationLogSpec {

    @YamlIgnore
    public LocationSpec.Id location;

    @YamlComment(text = {
            "Location events for each tick"
    })
    public Map<TickSpec.Id, LocationEventSpec> events = new LinkedHashMap<>();
}
