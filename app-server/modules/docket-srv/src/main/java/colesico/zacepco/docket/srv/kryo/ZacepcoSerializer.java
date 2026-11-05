package colesico.zacepco.docket.srv.kryo;

import colesico.zacepco.investigation.model.Stateless;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

import java.lang.reflect.Field;

public class ZacepcoSerializer<T> extends FieldSerializer<T> {

    public ZacepcoSerializer(Kryo kryo, Class<T> type) {
        super(kryo, type);
        removeAnnotatedFields(type);
    }

    private void removeAnnotatedFields(Class<?> type) {
        Class<?> current = type;
        while (current != null && current != Object.class) {
            for (Field field : current.getDeclaredFields()) {
                if (field.isAnnotationPresent(Stateless.class)) {
                    removeField(field.getName());
                }
            }
            current = current.getSuperclass();
        }
    }
}