package colesico.zacepco.docket.srv.jdbi;

import colesico.framework.jdbirec.FieldMediator;
import colesico.framework.jdbirec.RecordKitApi;
import colesico.zacepco.docket.srv.kryo.KryoFactory;
import colesico.zacepco.investigation.model.InvestigationState;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Pool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvestigationStateMediator implements FieldMediator<InvestigationState> {

    private final Pool<Kryo> kryoPool;

    public InvestigationStateMediator() {
        this.kryoPool = new Pool<Kryo>(true, false, 16) {
            @Override
            protected Kryo create() {
                return KryoFactory.create();
            }
        };
    }

    @Override
    public InvestigationState importField(String columnName, ResultSet rs) throws SQLException {

        byte[] bytes = rs.getBytes(columnName);
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        Kryo kryo = kryoPool.obtain();
        try (Input input = new Input(bytes)) {
            return kryo.readObject(input, InvestigationState.class);
        } finally {
            kryoPool.free(kryo);
        }
    }

    @Override
    public void exportField(InvestigationState fieldValue, String fieldName, RecordKitApi.FieldReceiver fr) {
        if (fieldValue == null) {
            fr.set(fieldName, null);
            return;
        }

        Kryo kryo = kryoPool.obtain();
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
             Output output = new Output(buffer)) {

            kryo.writeObject(output, fieldValue);
            output.flush();

            byte[] stateData = buffer.toByteArray();
            fr.set(fieldName, stateData);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to serialize InvestigationState", e);
        } finally {
            kryoPool.free(kryo);
        }
    }
}
