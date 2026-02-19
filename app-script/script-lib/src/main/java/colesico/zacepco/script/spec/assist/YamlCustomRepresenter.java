package colesico.zacepco.script.spec.assist;

import colesico.zacepco.script.spec.model.entity.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YamlCustomRepresenter  extends Representer {
    public YamlCustomRepresenter(DumperOptions options) {
        super(options);
        this.representers.put(LocalDate.class, new RepresentLocalDate());
        this.representers.put(LocationId.class, new RepresentEntityId());
        this.representers.put(PersonageId.class, new RepresentEntityId());
        this.representers.put(ClueId.class, new RepresentEntityId());
        this.representers.put(TimeId.class, new RepresentEntityId());
        this.representers.put(EntityId.class, new RepresentEntityId());
    }

    private class RepresentLocalDate implements Represent {
        @Override
        public Node representData(Object data) {
            LocalDate date = (LocalDate) data;
            String value = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            return representScalar(Tag.TIMESTAMP, value);
        }
    }

    private class RepresentEntityId implements Represent {
        @Override
        public Node representData(Object data) {
            EntityId entityId = (EntityId) data;
            String value = entityId.asString();
            return representScalar(Tag.STR, value);
        }
    }
}
