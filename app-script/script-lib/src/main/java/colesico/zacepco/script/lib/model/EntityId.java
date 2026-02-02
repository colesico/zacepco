package colesico.zacepco.script.lib.model;

import java.util.Objects;

public final class EntityId {

    public final IdCode code;
    public final Integer number;

    public EntityId(IdCode code, Integer number) {
        this.code = code;
        this.number = number;
    }

    public static EntityId parse(String entityIdStr){
        char code = entityIdStr.charAt(0);
        String number = entityIdStr.substring(1);
        return new EntityId(IdCode.fromCode(code),Integer.parseInt(number));
    }

    public IdCode getCode() {
        return code;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "" + code + number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntityId entityId = (EntityId) o;
        return code == entityId.code && Objects.equals(number, entityId.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, number);
    }
}
