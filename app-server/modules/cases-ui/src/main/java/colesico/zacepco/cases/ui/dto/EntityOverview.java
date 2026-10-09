package colesico.zacepco.cases.ui.dto;

import colesico.zacepco.script.model.setting.EntityId;

public class EntityOverview {

    public EntityId id;

    public String name;

    public String description;

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
