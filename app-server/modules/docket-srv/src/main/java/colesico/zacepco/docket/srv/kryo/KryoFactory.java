package colesico.zacepco.docket.srv.kryo;

import colesico.zacepco.investigation.model.DetectiveState;
import colesico.zacepco.investigation.model.InvestigationState;
import colesico.zacepco.investigation.model.LocationState;
import colesico.zacepco.investigation.model.PersonageState;
import com.esotericsoftware.kryo.Kryo;

public class KryoFactory {

    private static void registerClasses(Kryo kryo, Class<?>... classes){
        for (var clazz:classes){
            kryo.register(clazz, new ZacepcoSerializer<>(kryo, clazz));
        }
    }

    public static Kryo create() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        registerClasses(kryo,
                InvestigationState.class,
                LocationState.class,
                PersonageState.class,
                DetectiveState.class);
        return kryo;
    }
}
