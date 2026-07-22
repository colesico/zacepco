module colesico.zacepco.db {

    requires jakarta.inject;
    requires transitive colesico.framework.jdbi;
    requires transitive colesico.framework.jdbirec;
    requires transitive com.google.gson;
    requires transitive org.jdbi.v3.sqlite;
    requires org.apache.commons.lang3;

    requires colesico.framework.config;
    requires org.slf4j;


    exports colesico.zacepco.db.filestorage;
    exports colesico.zacepco.db.srv;

}