package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;


public final class TickSpec {

    public static final LocalDate DEFAULT_DATE = LocalDate.of(1970, 1, 1);

    @YamlIgnore
    public Id id;

    public LocalDateTime timestamp;

    public TickSpec() {
    }

    public TickSpec(String timestamp) {
        if (timestamp.length() <= 5) {
            timestamp = DEFAULT_DATE + "T" + timestamp;
        }
        this.timestamp = LocalDateTime.parse(timestamp);
    }

    public TickSpec(Id id, LocalDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public static final class Id extends TypedId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return TypedId.Type.TIME;
        }

    }
}
