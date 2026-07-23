module colesico.zacepco.storage {

    requires jakarta.inject;
    requires transitive colesico.framework.hikaricp;
    requires transitive colesico.framework.jdbi;
    requires transitive colesico.framework.jdbirec;
    requires transitive com.google.gson;
    requires transitive org.jdbi.v3.sqlite;
    requires org.apache.commons.lang3;

    requires colesico.framework.config;
    requires org.slf4j;


    exports colesico.zacepco.storage.filestorage;
    exports colesico.zacepco.storage.dbstorage;
    exports colesico.zacepco.storage.dbstorage.mediators;
    exports colesico.zacepco.storage.ioc to colesico.framework.ioc;

}