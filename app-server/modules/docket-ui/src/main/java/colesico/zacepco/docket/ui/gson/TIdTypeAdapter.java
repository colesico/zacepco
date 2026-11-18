package colesico.zacepco.docket.ui.gson;

import colesico.zacepco.script.model.scriptdoc.TId;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TIdTypeAdapter extends TypeAdapter<TId.Type> {

    @Override
    public void write(JsonWriter out, TId.Type type) throws IOException {
        if (type == null) {
            out.nullValue();
            return;
        }
        out.value(type.toString() + "?");
    }

    @Override
    public TId.Type read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
