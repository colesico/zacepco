package colesico.zacepco.script.model.scriptdoc;

import java.time.LocalDate;
import java.time.LocalDateTime;


public final class TickSpec {

    public static final LocalDate DEFAULT_DATE = LocalDate.of(1970, 1, 1);

    public Id id;

    public LocalDateTime timestamp;

    public TickSpec() {
    }

    public TickSpec(String tickValue) {
        String[] idTs = tickValue.split("=", 2);
        if (idTs.length < 2) {
            throw new IllegalArgumentException("Invalid tick format: " + tickValue + "; Accept: Tn=hh:mm or Tn=yyyy-mm-ddThh:mm");
        }
        var ts = idTs[1].trim();
        if (ts.length() <= 5) {
            ts = DEFAULT_DATE + "T" + ts;
        }
        this.timestamp = LocalDateTime.parse(ts);
        this.id = (Id) EntitySpec.UID.parse(idTs[0].trim());
    }

    public TickSpec(Id id, LocalDateTime timestamp) {
        this.id = id;
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
