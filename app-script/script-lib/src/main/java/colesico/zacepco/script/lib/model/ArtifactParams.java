package colesico.zacepco.script.lib.model;

import java.util.Map;

public class ArtifactParams {

    public Integer artifactId;

    /**
     * Param values
     */
    public Map<String,String> params;

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
