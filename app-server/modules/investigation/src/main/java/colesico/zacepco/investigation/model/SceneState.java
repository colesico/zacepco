package colesico.zacepco.investigation.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class SceneState {

    @Stateless
    public String title;

    @Stateless
    public Integer rows;

    @Stateless
    public Integer cols;

    public final Map<String, LocationState> locations = new LinkedHashMap<>();
}
