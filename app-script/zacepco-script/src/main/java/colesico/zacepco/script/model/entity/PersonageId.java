package colesico.zacepco.script.model.entity;

public final class PersonageId extends EntityId {

    public PersonageId(EntityType type, String value) {
        super(type, value);
    }

    public PersonageId(String value) {
        super(EntityType.PERSONAGE, value);
    }
}
