package colesico.zacepco.scriptpack.yaml;

import colesico.zacepco.scriptpack.model.script.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.comments.CommentLine;
import org.yaml.snakeyaml.comments.CommentType;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.*;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class YamlCustomRepresenter extends Representer {

    private Set<String> oneTimeComments = new HashSet<>();

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final DateTimeFormatter TIME_ONLY_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public YamlCustomRepresenter(DumperOptions options) {
        super(options);

        // Устанавливаем кастомный PropertyUtils, который не сортирует поля
        UnsortedPropertyUtils propertyUtils = new UnsortedPropertyUtils();
        propertyUtils.setSkipMissingProperties(true);
        propertyUtils.setBeanAccess(BeanAccess.FIELD);
        this.setPropertyUtils(propertyUtils);

        // Регистрация кастомных репрезентеров
        this.representers.put(LocalDate.class, new RepresentLocalDate());
        this.representers.put(LocalDateTime.class, new RepresentLocalDateTime());
        this.representers.put(LocationSpec.Id.class, new RepresentEntityId());
        this.representers.put(PersonageSpec.Id.class, new RepresentEntityId());
        this.representers.put(ClueSpec.Id.class, new RepresentEntityId());
        this.representers.put(TickSpec.Id.class, new RepresentEntityId());
        this.representers.put(DetectiveSpec.Id.class, new RepresentEntityId());
        this.representers.put(EntitySpec.UID.class, new RepresentEntityId());

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
            var valueNode = tuple.getValueNode();

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
                if (!ann.inline() && ann.topBlank()) {
                    commentLines.add(new CommentLine(null, null, "", CommentType.BLANK_LINE));
                }
                for (var commentStr : ann.text()) {
                    var textLine = commentStr.trim();
                    commentLines.add(new CommentLine(
                            null, null, " " + textLine,
                            ann.inline() ? CommentType.IN_LINE : CommentType.BLOCK)
                    );
                }

                if (!commentLines.isEmpty()) {
                    if (ann.inline()) {
                        valueNode.setInLineComments(commentLines);
                    } else {
                        keyNode.setBlockComments(commentLines);
                    }
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

        if (propertyValue instanceof Set) {
            propertyValue = new ArrayList<>((Set<?>) propertyValue);
        }

        NodeTuple tuple = super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);

        if (tuple != null) {
            YamlCompact compactAnnotation = property.getAnnotation(YamlCompact.class);
            if (compactAnnotation != null) {
                Node valueNode = tuple.getValueNode();
                if (valueNode instanceof SequenceNode) {
                    ((SequenceNode) valueNode).setFlowStyle(DumperOptions.FlowStyle.FLOW);
                }
            }
        }
        return tuple;
    }

    private class RepresentLocalDate implements Represent {
        @Override
        public Node representData(Object data) {
            LocalDate date = (LocalDate) data;
            String value = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            return representScalar(Tag.STR, value);
        }
    }

    private class RepresentLocalDateTime implements Represent {
        @Override
        public Node representData(Object data) {

            LocalDateTime dateTime = (LocalDateTime) data;

            String value;

            if (dateTime.toLocalDate().equals(YamlCustomConstructor.defaultDate)) {
                value = dateTime.format(TIME_ONLY_FORMATTER);
            } else {
                value = dateTime.format(DATE_TIME_FORMATTER);
            }

            return representScalar(Tag.STR, value);
        }
    }

    private class RepresentEntityId implements Represent {
        @Override
        public Node representData(Object data) {
            EntitySpec.UID entityId = (EntitySpec.UID) data;
            String id = entityId.toString();
            return representScalar(Tag.STR, id);
        }
    }
}
