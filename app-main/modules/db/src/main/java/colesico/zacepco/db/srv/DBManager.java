package colesico.zacepco.db.srv;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.locator.ClasspathSqlLocator;


@Service
@Transactional
public class DBManager {

    private final Provider<Handle> handle;

    public DBManager(Provider<Handle> handle) {
        this.handle = handle;
    }

    public void initDB() {
        ClasspathSqlLocator locator = ClasspathSqlLocator.create();

        String sqlScript = locator.getResource("META-INF/db-schema.sql");

        try (var script = handle.get().createScript(sqlScript)) {
            script.executeAsSeparateStatements();
            System.out.println("Database schema successfully initialized.");
        } catch (Exception e) {
            System.err.println("Failed to execute database schema script: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
