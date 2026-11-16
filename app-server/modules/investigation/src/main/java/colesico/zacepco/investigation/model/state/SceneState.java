package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.LocationSpec;

import java.util.LinkedHashMap;
import java.util.Map;

public class SceneState {

    public final Map<LocationSpec.Id, LocationState> locations = new LinkedHashMap<>();

}
