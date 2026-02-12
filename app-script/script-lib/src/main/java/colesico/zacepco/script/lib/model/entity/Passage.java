package colesico.zacepco.script.lib.model.entity;

import java.util.Set;

public class Passage {
    
    public Set<EntityId> locations;

    /**
     * Is hidden passage
     */
    public boolean hidden;

    public Set<EntityId> getLocations() {
        return locations;
    }

    public void setLocations(Set<EntityId> locations) {
        this.locations = locations;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
