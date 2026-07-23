package colesico.zacepco.storage.dbstorage;

import colesico.framework.config.Config;
import colesico.framework.jdbi.JdbiConfigPrototype;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

@Config
public class JdbiConfig extends JdbiConfigPrototype {

    private final DataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger(Jdbi.class);

    public JdbiConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource dataSource() {
        return dataSource;
    }

    @Override
    public void configure(Jdbi jdbi) {
        jdbi.installPlugin(new SQLitePlugin());
        jdbi.setSqlLogger(new SqlLogger() {
            @Override
            public void logBeforeExecution(StatementContext context) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Prepared SQL: " + context.getParsedSql().getSql());
                    logger.debug("Executed SQL: " + context.getStatement().toString());
                    logger.debug("SQL Bindings: " + context.getBinding());
                }
            }

            @Override
            public void logException(StatementContext context, SQLException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("SQL Error", ex);
                    logger.debug("Prepared SQL: " + context.getParsedSql().getSql());
                    logger.debug("SQL Bindings: " + context.getBinding());
                }
            }
        });
    }
}
