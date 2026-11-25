package colesico.zacepco.docket.ui.config;

import colesico.framework.config.Config;
import colesico.framework.restlet.gson.GsonOptionsPrototype;
import colesico.zacepco.docket.ui.gson.TIdTypeAdapter;
import colesico.zacepco.docket.ui.gson.TIdAdapter;
import colesico.zacepco.script.model.scriptdoc.*;
import com.google.gson.GsonBuilder;

@Config
public class RestletGsonOptions extends GsonOptionsPrototype {
    @Override
    public GsonBuilder configure(GsonBuilder gsonBuilder) {
        gsonBuilder
                .registerTypeAdapter(LocationSpec.Id.class, new TIdAdapter())
                .registerTypeAdapter(WaySpec.Id.class, new TIdAdapter())
                .registerTypeAdapter(PersonageSpec.Id.class, new TIdAdapter())
                .registerTypeAdapter(ClueSpec.Id.class, new TIdAdapter())
                .registerTypeAdapter(TickSpec.Id.class, new TIdAdapter())
                .registerTypeAdapter(DetectiveSpec.Id.class, new TIdAdapter())
                .registerTypeAdapter(TId.Type.class, new TIdTypeAdapter())
        ;
        return gsonBuilder;
    }
}
