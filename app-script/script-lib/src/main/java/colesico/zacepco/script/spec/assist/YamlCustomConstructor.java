package colesico.zacepco.script.spec.assist;

import colesico.zacepco.script.spec.model.entity.Entity;
import colesico.zacepco.script.spec.model.entity.EntityId;
import colesico.zacepco.script.spec.model.entity.LocationId;
import colesico.zacepco.script.spec.model.entity.PersonageId;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

import java.time.LocalDate;

public class YamlCustomConstructor extends Constructor {

    public YamlCustomConstructor() {
        super(new LoaderOptions());
        this.yamlConstructors.put(new Tag(EntityId.class), new ConstructEntity());
        this.yamlConstructors.put(new Tag(PersonageId.class), new ConstructEntity());
        this.yamlConstructors.put(new Tag(LocationId.class), new ConstructEntity());
    }

    @Override
    protected Construct getConstructor(Node node) {
        // Если узел распознан как дата (TIMESTAMP), отдаем наш парсер
        if (Tag.TIMESTAMP.equals(node.getTag())) {
            return new ConstructLocalDate();
        }
        return super.getConstructor(node);
    }

    static class ConstructLocalDate extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = ((ScalarNode) node).getValue();
            // Берем первые 10 символов (гггг-мм-дд) и парсим
            return LocalDate.parse(value.substring(0, 10));
        }
    }

    static class ConstructEntity extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = ((ScalarNode) node).getValue();
            return EntityId.parse(value);
        }
    }
}
