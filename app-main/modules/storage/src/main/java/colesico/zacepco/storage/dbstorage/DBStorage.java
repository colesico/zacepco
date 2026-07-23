package colesico.zacepco.storage.dbstorage;

import colesico.framework.ioc.listener.PostConstruct;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.locator.ClasspathSqlLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Transactional
public class DBStorage {

    private static final Logger log = LoggerFactory.getLogger(DBStorage.class);
    private final Provider<Handle> handle;

    public DBStorage(Provider<Handle> handle) {
        this.handle = handle;
    }

    /**
     * Initialize sqlight db file
     */
    @PostConstruct
    public void init() {
        try {
            var handle = this.handle.get();
            var databseUrl = handle.getConnection().getMetaData().getURL();
            log.info("Initialize database '{}'", databseUrl);
            ClasspathSqlLocator locator = ClasspathSqlLocator.create();

            String sqlScript = locator.getResource("META-INF/db-schema.sql");

            try (var script = handle.createScript(sqlScript)) {
                script.executeAsSeparateStatements();
                System.out.println("Database schema successfully initialized.");
            }

        } catch (Exception e) {
            System.err.println("Failed initialize database  " + e.getMessage());
            e.printStackTrace();
        }

    }
}
