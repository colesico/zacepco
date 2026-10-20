package colesico.zacepco.respack.model.setting;

import colesico.zacepco.respack.yaml.YamlComment;

public class EntityReference {

    @YamlComment(text = {
            "Referenced script USN (target script)",
            "Must be mentioned in 'include' section"
    })
    public String usn;

    @YamlComment(text = "Referenced script entity id (target entity)")
    public EntityId entity;

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public EntityId getEntity() {
        return entity;
    }

    public void setEntity(EntityId entity) {
        this.entity = entity;
    }
}
