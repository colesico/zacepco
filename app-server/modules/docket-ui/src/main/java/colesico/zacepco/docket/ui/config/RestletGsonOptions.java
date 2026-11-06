package colesico.zacepco.docket.ui.config;

import colesico.framework.config.Config;
import colesico.framework.restlet.gson.GsonOptionsPrototype;
import colesico.zacepco.docket.ui.gson.EntityUIDTypeAdapter;
import colesico.zacepco.script.model.scriptdoc.*;
import com.google.gson.GsonBuilder;

@Config
public class RestletGsonOptions extends GsonOptionsPrototype {
    @Override
    public GsonBuilder configure(GsonBuilder gsonBuilder) {
        gsonBuilder
                .registerTypeAdapter(LocationSpec.Id.class, new EntityUIDTypeAdapter())
                .registerTypeAdapter(PersonageSpec.Id.class, new EntityUIDTypeAdapter())
                .registerTypeAdapter(ClueSpec.Id.class, new EntityUIDTypeAdapter())
                .registerTypeAdapter(TickSpec.Id.class, new EntityUIDTypeAdapter())
                .registerTypeAdapter(DetectiveSpec.Id.class, new EntityUIDTypeAdapter())
        ;
        return gsonBuilder;
    }
}
