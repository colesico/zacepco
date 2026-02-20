package colesico.zacepco.script.assist;

import colesico.zacepco.script.model.entity.*;
import colesico.zacepco.script.model.entity.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @Override
    protected Set<Property> getProperties(Class<? extends Object> type) {
        // Получаем свойства через PropertyUtils и сохраняем их в LinkedHashSet (сохраняет порядок вставки)
        return new LinkedHashSet<>(getPropertyUtils().getProperties(type));
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
