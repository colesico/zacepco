package colesico.zacepco.script.lib.yaml;

import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.FieldProperty;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Custom utils to prevent class field order
 */
public class UnsortedPropertyUtils extends PropertyUtils {

    @Override
    public Set<Property> getProperties(Class<? extends Object> type, BeanAccess bAccess) {
        Set<Property> properties = new LinkedHashSet<>();

        for (Property property : this.getPropertiesMap(type, bAccess).values()) {
            if (property.isReadable() && (this.isAllowReadOnlyProperties() || property.isWritable())) {
                properties.add(property);
            }
        }
        return properties;
    }

    @Override
    protected Map<String, Property> getPropertiesMap(Class<?> type, BeanAccess bAccess) {
        if (bAccess != BeanAccess.FIELD) {
            return super.getPropertiesMap(type, bAccess);
        }

        List<Class<?>> hierarchy = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            hierarchy.add(c);
        }

        Collections.reverse(hierarchy);

        Map<String, Property> properties = new LinkedHashMap<>();
        for (Class<?> c : hierarchy) {
            for (Field field : c.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && !properties.containsKey(field.getName())) {
                    properties.put(field.getName(), new FieldProperty(field));
                }
            }
        }
        return properties;
    }
}
