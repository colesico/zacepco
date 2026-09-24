module colesico.zacepco.catalog.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires transitive colesico.zacepco.common.srv;
    requires transitive colesico.zacepco.script;


    exports colesico.zacepco.catalog.srv.ioc;
    exports colesico.zacepco.catalog.srv.model;
    exports colesico.zacepco.catalog.srv.dao;
    exports colesico.zacepco.catalog.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}