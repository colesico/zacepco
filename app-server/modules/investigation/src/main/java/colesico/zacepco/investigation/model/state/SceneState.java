package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.LocationSpec;
import colesico.zacepco.script.model.scriptdoc.WaySpec;

import java.util.LinkedHashMap;
import java.util.Map;

public class SceneState {

    public final Map<LocationSpec.Id, LocationState> locations = new LinkedHashMap<>();
    public final Map<WaySpec.Id, WayState> ways = new LinkedHashMap<>();

}
