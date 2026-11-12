package colesico.zacepco.docket.srv.kryo;

import colesico.zacepco.investigation.model.state.*;
import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.DetectiveSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;
import colesico.zacepco.script.model.scriptdoc.PersonageSpec;
import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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

        kryo.register(PersonageSpec.Id.class, new TIdSerializer(), 101);
        kryo.register(LocationSpec.Id.class, new TIdSerializer(), 102);
        kryo.register(ClueSpec.Id.class, new TIdSerializer(), 103);
        kryo.register(DetectiveSpec.Id.class, new TIdSerializer(), 104);

        registerBeanClasses(kryo,
                InvestigationState.class,
                LocationState.class,
                PersonageState.class,
                DetectiveState.class,
                SceneState.class,
                ScriptRef.class
        );

        return kryo;
    }
}
