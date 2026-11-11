package colesico.zacepco.docket.ui.gson;

import colesico.zacepco.script.model.scriptdoc.TypedId;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class EntityUIDTypeAdapter extends TypeAdapter<TypedId> {
    @Override
    public void write(JsonWriter out, TypedId uid) throws IOException {
        if (uid == null) {
            out.nullValue();
            return;
        }
        out.value(uid.toString());
    }

    @Override
    public TypedId read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
