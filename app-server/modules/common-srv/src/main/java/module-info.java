module colesico.zacepco.common.srv {

    requires transitive org.slf4j;

    requires transitive colesico.framework.config;
    requires transitive colesico.framework.translation;
    requires transitive colesico.framework.validation;
    requires transitive colesico.framework.transaction;
    requires transitive colesico.framework.hikaricp;
    requires transitive colesico.framework.jdbi;
    requires transitive colesico.framework.jdbirec;
    requires transitive org.jdbi.v3.postgres;

    exports colesico.zacepco.common.srv.filestorage;
    exports colesico.zacepco.common.srv.dbstorage;
    exports colesico.zacepco.common.srv.ioc to colesico.framework.ioc;
    exports colesico.zacepco.common.srv.service;
    exports colesico.zacepco.common.srv.utils;

}