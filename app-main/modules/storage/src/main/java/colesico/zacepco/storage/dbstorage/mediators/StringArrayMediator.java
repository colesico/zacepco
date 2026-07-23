package colesico.zacepco.storage.dbstorage.mediators;

import colesico.framework.jdbirec.FieldMediator;
import colesico.framework.jdbirec.RecordKitApi;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Json based string array mediator
 */
public class StringArrayMediator implements FieldMediator<String[]> {

    private static final Gson gson = new Gson();

    @Override
    public String[] importField(String columnName, ResultSet rs) throws SQLException {
        var json = rs.getString(columnName);
        return gson.fromJson(json, String[].class);
    }

    @Override
    public void exportField(String[] value, String fieldName, RecordKitApi.FieldReceiver fr) {
        fr.set(fieldName, value == null ? null : gson.toJson(value));
    }
}
