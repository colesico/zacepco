package colesico.zacepco.script.lib.model.entity;

import java.util.List;

public class Passage {

    public List<EntityId> locations;

    /**
     * Is hidden passage
     */
    public boolean hidden;

    public List<EntityId> getLocations() {
        return locations;
    }

    public void setLocations(List<EntityId> locations) {
        this.locations = locations;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "locations=" + locations +
                '}';
    }
}
