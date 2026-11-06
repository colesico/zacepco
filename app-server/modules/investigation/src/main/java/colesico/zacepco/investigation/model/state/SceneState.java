package colesico.zacepco.investigation.model.state;

import java.util.LinkedHashMap;
import java.util.Map;

public class SceneState {

    @Stateless
    public String name;

    @Stateless
    public SceneSize size;

    public final Map<String, LocationState> locations = new LinkedHashMap<>();
}
