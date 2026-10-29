module colesico.zacepco.core.srv {

    requires transitive org.slf4j;

    requires transitive colesico.framework.config;
    requires transitive colesico.framework.translation;
    requires transitive colesico.framework.validation;
    requires transitive colesico.framework.transaction;
    requires transitive colesico.framework.hikaricp;
    requires transitive colesico.framework.jdbi;
    requires transitive colesico.framework.jdbirec;
    requires transitive org.jdbi.v3.sqlite;
    requires transitive java.desktop;

    exports colesico.zacepco.core.srv.dbstorage;
    exports colesico.zacepco.core.srv.ioc to colesico.framework.ioc;
    exports colesico.zacepco.core.srv.service;
    exports colesico.zacepco.core.srv.utils;

}