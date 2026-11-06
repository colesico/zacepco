package colesico.zacepco.docket.srv.kryo;

import colesico.zacepco.investigation.model.state.*;
import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class KryoFactory {

    private static void registerBeanClasses(Kryo kryo, Class<?>... classes){
        for (var clazz:classes){
            kryo.register(clazz, new ZacepcoSerializer<>(kryo, clazz));
        }
    }

    public static Kryo create() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(true);

        kryo.register(LinkedHashMap.class);
        kryo.register(ArrayList.class);

        registerBeanClasses(kryo,
                InvestigationState.class,
                LocationState.class,
                PersonageState.class,
                DetectiveState.class,
                SceneState.class,
                ScriptRef.class);

        return kryo;
    }
}
