package colesico.zacepco.common.srv.dbstorage;

import colesico.framework.assist.StringUtils;
import colesico.framework.config.Config;
import colesico.framework.config.DefaultMessage;
import colesico.framework.hikaricp.HikariProperties;
import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@Config
@DefaultMessage
public class HikariCpConfig extends HikariProperties {

    public static final String  JDBC_URL_ENV = "JDBC_URL";

    private final Logger log = LoggerFactory.getLogger(HikariCpConfig.class);

    @Override
    protected HikariConfig createConfig(Properties props) {
        var config = new HikariConfig(props);

        var jdbcUrl = System.getenv().get(JDBC_URL_ENV);
        if (!StringUtils.isBlank(jdbcUrl)) {
            config.setJdbcUrl(jdbcUrl);
        }

        log.info("Actual JdbcURL: {}", config.getJdbcUrl());
        return config;
    }
}
