package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Significant entities  for location search/interrogation/expertise
 */
public class SignificantEntities {

    public List<Integer> personage;
    public List<Integer> location;
    public List<Integer> artifact;
    public List<Integer> time;

    public List<Integer> getPersonage() {
        return personage;
    }

    public void setPersonage(List<Integer> personage) {
        this.personage = personage;
    }

    public List<Integer> getLocation() {
        return location;
    }

    public void setLocation(List<Integer> location) {
        this.location = location;
    }

    public List<Integer> getArtifact() {
        return artifact;
    }

    public void setArtifact(List<Integer> artifact) {
        this.artifact = artifact;
    }

    public List<Integer> getTime() {
        return time;
    }

    public void setTime(List<Integer> time) {
        this.time = time;
    }
}
