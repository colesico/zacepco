package colesico.zacepco.docket.ui.gson;

import colesico.zacepco.script.model.scriptdoc.TId;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class EntityUIDTypeAdapter extends TypeAdapter<TId> {
    @Override
    public void write(JsonWriter out, TId uid) throws IOException {
        if (uid == null) {
            out.nullValue();
            return;
        }
        out.value(uid.toString());
    }

    @Override
    public TId read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
