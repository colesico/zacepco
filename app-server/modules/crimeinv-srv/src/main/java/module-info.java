module colesico.zacepco.crimeinv.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires transitive colesico.zacepco.common.srv;
    requires transitive colesico.zacepco.script;


    exports colesico.zacepco.crimeinv.srv.ioc;
    exports colesico.zacepco.crimeinv.srv.model;
    exports colesico.zacepco.crimeinv.srv.dao;
    exports colesico.zacepco.crimeinv.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}