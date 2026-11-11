package colesico.zacepco.script.yaml;

import colesico.zacepco.script.model.scriptdoc.TypedId;
import colesico.zacepco.script.model.scriptdoc.TickSpec;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static colesico.zacepco.script.model.scriptdoc.TickSpec.DEFAULT_DATE;

public class YamlCustomConstructor extends Constructor {

    public YamlCustomConstructor() {
        super(new LoaderOptions());
        getPropertyUtils().setBeanAccess(BeanAccess.PROPERTY);
    }

    @Override
    protected Construct getConstructor(Node node) {

        if (node.getType() == TickSpec.class) {
            return new ConstructTickSpec();
        }

        if (node.getType() == LocalDateTime.class) {
            return new ConstructLocalDateTime();
        }

        if (node.getType() == LocalDate.class) {
            return new ConstructLocalDate();
        }

        if (TypedId.class.isAssignableFrom(node.getType())) {
            return new ConstructTID();
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
            if (value.length() <= 5) {
                value = DEFAULT_DATE + "T" + value;
            }
            return LocalDateTime.parse(value);
        }
    }

    static class ConstructTID extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = ((ScalarNode) node).getValue();
            return TypedId.parse(value);
        }
    }

    static class ConstructTickSpec extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String ts = ((ScalarNode) node).getValue();
            return new TickSpec(ts);
        }
    }
}
