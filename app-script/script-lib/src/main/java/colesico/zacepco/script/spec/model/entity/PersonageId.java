package colesico.zacepco.script.spec.model.entity;

public final class PersonageId extends EntityId {

    public PersonageId(IdType type, String value) {
        super(type, value);
    }

    public PersonageId(String value) {
        super(IdType.PERSONAGE, value);
    }
}
