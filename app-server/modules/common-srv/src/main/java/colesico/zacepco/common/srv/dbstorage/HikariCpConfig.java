package colesico.zacepco.common.srv.dbstorage;

import colesico.framework.assist.StringUtils;
import colesico.framework.config.Config;
import colesico.framework.config.DefaultMessage;
import colesico.framework.hikaricp.HikariProperties;
import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Config
@DefaultMessage
public class HikariCpConfig extends HikariProperties {

    public static final String ZACEPCO_JDBC_URL_ENV = "ZACEPCO_JDBC_URL";

    private final Logger log = LoggerFactory.getLogger(HikariCpConfig.class);

    @Override
    protected HikariConfig createConfig(Properties props) {
        var config = new HikariConfig(props);

        var jdbcUrl = System.getenv().get(ZACEPCO_JDBC_URL_ENV);
        if (!StringUtils.isBlank(jdbcUrl)) {
            config.setJdbcUrl(jdbcUrl);
        }

        createDbDirectory(config.getJdbcUrl());

        log.info("Actual JdbcURL: {}", config.getJdbcUrl());
        return config;
    }

    private void createDbDirectory(String jdbcUrl) {
        String path = jdbcUrl.substring("jdbc:sqlite:".length());
        int q = path.indexOf('?');
        if (q >= 0) path = path.substring(0, q);
        if (path.isEmpty() || path.equals(":memory:")) return;
        try {
            Files.createDirectories(Paths.get(path).toAbsolutePath().getParent());
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot create DB directory", e);
        }
    }
}
