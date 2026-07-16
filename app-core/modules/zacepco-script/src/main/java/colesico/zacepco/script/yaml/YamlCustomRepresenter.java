package colesico.zacepco.script.yaml;

import colesico.zacepco.script.model.setting.ClueId;
import colesico.zacepco.script.model.setting.TimeId;
import colesico.zacepco.script.model.setting.EntityId;
import colesico.zacepco.script.model.setting.PersonageId;
import colesico.zacepco.script.model.setting.LocationId;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.comments.CommentLine;
import org.yaml.snakeyaml.comments.CommentType;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.*;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class YamlCustomRepresenter extends Representer {

    private Set<String> oneTimeComments = new HashSet<>();

    public YamlCustomRepresenter(DumperOptions options) {
        super(options);

        // Устанавливаем кастомный PropertyUtils, который не сортирует поля
        UnsortedPropertyUtils propertyUtils = new UnsortedPropertyUtils();
        propertyUtils.setSkipMissingProperties(true);
        propertyUtils.setBeanAccess(BeanAccess.FIELD);
        this.setPropertyUtils(propertyUtils);

        // Регистрация кастомных репрезентеров
        this.representers.put(LocalDate.class, new RepresentLocalDate());
        this.representers.put(LocationId.class, new RepresentEntityId());
        this.representers.put(PersonageId.class, new RepresentEntityId());
        this.representers.put(ClueId.class, new RepresentEntityId());
        this.representers.put(TimeId.class, new RepresentEntityId());
        this.representers.put(EntityId.class, new RepresentEntityId());
    }

    @Override
    protected Set<Property> getProperties(Class<? extends Object> type) {
        return getPropertyUtils().getProperties(type);
    }

    @Override
    protected MappingNode representJavaBean(Set<Property> properties, Object javaBean) {
        MappingNode mappingNode = super.representJavaBean(properties, javaBean);
        List<NodeTuple> tuples = mappingNode.getValue();

        Map<String, Property> propertyMap = new HashMap<>();
        for (Property prop : properties) {
            propertyMap.put(prop.getName(), prop);
        }

        for (NodeTuple tuple : tuples) {
            Node keyNode = tuple.getKeyNode();

            if (keyNode instanceof ScalarNode) {
                String propertyName = ((ScalarNode) keyNode).getValue();
                Property prop = propertyMap.get(propertyName);

                if (prop == null) {
                    continue;
                }

                YamlComment ann = prop.getAnnotation(YamlComment.class);
                if (ann == null) {
                    continue;
                }

                if (ann.oneTime()) {
                    String commentKey = String.join("|", ann.text());
                    if (!oneTimeComments.add(commentKey)) {
                        continue;
                    }
                }

                List<CommentLine> commentLines = new ArrayList<>();
                for (var commentStr : ann.text()) {
                    commentLines.add(new CommentLine(
                            null, null, " " + commentStr.trim(), CommentType.BLOCK)
                    );
                }

                if (!commentLines.isEmpty()) {
                    keyNode.setBlockComments(commentLines);
                }
            }
        }
        return mappingNode;
    }

    @Override
    protected NodeTuple representJavaBeanProperty(
            Object javaBean, Property property, Object propertyValue, Tag customTag) {

        if (propertyValue == null) {
            return null;
        }

        if (propertyValue instanceof java.util.Collection && ((java.util.Collection<?>) propertyValue).isEmpty()) {
            return null;
        }
        if (propertyValue instanceof java.util.Map && ((java.util.Map<?, ?>) propertyValue).isEmpty()) {
            return null;
        }

        return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
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
            String id = entityId.getId();
            return representScalar(Tag.STR, id);
        }
    }
}
