package colesico.zacepco.docket.ui.gson;

import colesico.zacepco.script.model.scriptdoc.EntitySpec;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class EntityUIDTypeAdapter extends TypeAdapter<EntitySpec.UID> {
    @Override
    public void write(JsonWriter out, EntitySpec.UID uid) throws IOException {
        if (uid == null) {
            out.nullValue();
            return;
        }
        out.value(uid.toString());
    }

    @Override
    public EntitySpec.UID read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
