package colesico.zacepco.docket.srv.kryo;

import colesico.zacepco.investigation.model.state.*;
import colesico.zacepco.script.model.scriptdoc.*;
import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class KryoFactory {

    private static void registerBeanClasses(Kryo kryo, Class<?>... classes) {
        for (var clazz : classes) {
            kryo.register(clazz, new ZacepcoSerializer<>(kryo, clazz));
        }
    }

    public static Kryo create() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(true);

        kryo.register(ArrayList.class, 11);
        kryo.register(LinkedHashMap.class, 12);
        kryo.register(LinkedHashSet.class, 13);

        kryo.register(PersonageSpec.Id.class, new TIdSerializer(), 101);
        kryo.register(LocationSpec.Id.class, new TIdSerializer(), 102);
        kryo.register(WaySpec.Id.class, new TIdSerializer(), 103);
        kryo.register(ClueSpec.Id.class, new TIdSerializer(), 104);
        kryo.register(DetectiveSpec.Id.class, new TIdSerializer(), 105);

        registerBeanClasses(kryo,
                InvestigationState.class,
                PersonageState.class,
                LocationState.class,
                WayState.class,
                ClueState.class,
                DetectiveState.class,
                SceneState.class,
                ScriptRef.class
        );

        return kryo;
    }
}
