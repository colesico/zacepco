module colesico.zacepco.storage {

    requires transitive org.slf4j;

    requires transitive colesico.framework.config;
    requires transitive colesico.framework.translation;
    requires transitive colesico.framework.transaction;
    requires transitive colesico.framework.hikaricp;
    requires transitive colesico.framework.jdbi;
    requires transitive colesico.framework.jdbirec;
    requires transitive org.jdbi.v3.postgres;

    exports colesico.zacepco.storage.filestorage;
    exports colesico.zacepco.storage.dbstorage;
    exports colesico.zacepco.storage.ioc to colesico.framework.ioc;

}