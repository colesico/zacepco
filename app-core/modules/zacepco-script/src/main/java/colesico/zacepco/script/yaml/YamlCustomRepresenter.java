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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class YamlCustomRepresenter extends Representer {

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
        MappingNode node = super.representJavaBean(properties, javaBean);

        List<Property> propList = new ArrayList<>(properties);
        List<NodeTuple> tuples = node.getValue();

        for (int i = 0; i < tuples.size(); i++) {
            if (i >= propList.size()) break;

            Property prop = propList.get(i);
            YamlComment ann = prop.getAnnotation(YamlComment.class);

            if (ann != null) {
                Node keyNode = tuples.get(i).getKeyNode();
                String[] commentStrs = ann.value().split("\\R");
                List<CommentLine> commentLines = new ArrayList<>();
                for (var commentStr : commentStrs) {
                    commentLines.add(new CommentLine(
                            null, null, " " + commentStr, CommentType.BLOCK)
                    );
                }
                keyNode.setBlockComments(commentLines);
            }
        }
        return node;
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
