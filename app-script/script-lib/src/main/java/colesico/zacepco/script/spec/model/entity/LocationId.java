package colesico.zacepco.script.spec.model.entity;

public final class LocationId extends EntityId{

    private LocationId(IdType type, String value) {
        super(IdType.LOCATION, value);
    }

    public LocationId(String value) {
        super(IdType.LOCATION, value);
    }

}
