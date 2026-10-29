package colesico.zacepco.scriptpack.model.script;

import java.time.LocalDateTime;

public class TickSpec {

    public Id id;

    public LocalDateTime timestamp;

    public TickSpec() {
    }

    public TickSpec(Id id, LocalDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static final class Id extends EntitySpec.UID {

        public Id(String key) {
            super(key);
        }

        @Override
        public EntitySpec.Type type() {
            return EntitySpec.Type.TIME;
        }

    }
}
