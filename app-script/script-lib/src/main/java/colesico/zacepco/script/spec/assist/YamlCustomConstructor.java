package colesico.zacepco.script.spec.assist;

import colesico.zacepco.script.spec.model.entity.*;
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
    }

    @Override
    protected Construct getConstructor(Node node) {

        // Если узел распознан как дата (TIMESTAMP)
        if (Tag.TIMESTAMP.equals(node.getTag())) {
            return new ConstructLocalDate();
        }

        // У поля супер тип EntityId?
        if (EntityId.class.isAssignableFrom(node.getType())) {
            return new ConstructEntityId();
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

    static class ConstructEntityId extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = ((ScalarNode) node).getValue();
            return EntityId.parse(value);
        }
    }
}
