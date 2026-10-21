package colesico.zacepco.respack.model.script;

public final class LocationId extends EntityId {

    private LocationId(EntityType type, String value) {
        super(EntityType.LOCATION, value);
    }

    public LocationId(String value) {
        super(EntityType.LOCATION, value);
    }

}
