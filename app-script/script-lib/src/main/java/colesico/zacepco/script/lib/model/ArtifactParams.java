package colesico.zacepco.script.lib.model;

import java.util.Map;

public class ArtifactParams {
    public Integer artifactId;
    public Map<String,String> parameters;

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
