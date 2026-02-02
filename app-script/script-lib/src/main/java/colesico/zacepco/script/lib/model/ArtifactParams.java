package colesico.zacepco.script.lib.model;

import java.util.Map;

public class ArtifactParams {

    /**
     * Artifact id
     */
    public Integer artifact;

    /**
     * Param values
     */
    public Map<String,String> params;

    public Integer getArtifact() {
        return artifact;
    }

    public void setArtifact(Integer artifact) {
        this.artifact = artifact;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
