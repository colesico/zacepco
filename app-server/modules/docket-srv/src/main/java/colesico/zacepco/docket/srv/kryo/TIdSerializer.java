package colesico.zacepco.docket.srv.kryo;

import colesico.zacepco.script.model.scriptdoc.TId;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class TIdSerializer extends Serializer<TId> {

    @Override
    public void write(Kryo kryo, Output output, TId tId) {
        output.writeString(tId.toString());
    }

    @Override
    public TId read(Kryo kryo, Input input, Class<? extends TId> aClass) {
        return TId.parse(input.readString());
    }
}
