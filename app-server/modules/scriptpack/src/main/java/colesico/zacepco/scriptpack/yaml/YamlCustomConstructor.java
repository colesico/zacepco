package colesico.zacepco.scriptpack.yaml;

import colesico.zacepco.scriptpack.model.script.Entity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YamlCustomConstructor extends Constructor {

    public static final LocalDate defaultDate = LocalDate.of(1970, 1, 1);

    public YamlCustomConstructor() {
        super(new LoaderOptions());
        getPropertyUtils().setBeanAccess(BeanAccess.PROPERTY);
    }

    @Override
    protected Construct getConstructor(Node node) {
        if (node.getType() == LocalDateTime.class) {
            return new ConstructLocalDateTime();
        }
        if (node.getType() == LocalDate.class) {
            return new ConstructLocalDate();
        }

        if (Entity.UID.class.isAssignableFrom(node.getType())) {
            return new ConstructEntityId();
        }

        return super.getConstructor(node);
    }

    static class ConstructLocalDate extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = ((ScalarNode) node).getValue();
            return LocalDate.parse(value.substring(0, 10));
        }
    }

    static class ConstructLocalDateTime extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = ((ScalarNode) node).getValue().trim();
            if (value.length()<=5) {
                value = defaultDate +"T"+ value;
            }
            return LocalDateTime.parse(value);
        }
    }

    static class ConstructEntityId extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = ((ScalarNode) node).getValue();
            return Entity.UID.parse(value);
        }
    }
}
