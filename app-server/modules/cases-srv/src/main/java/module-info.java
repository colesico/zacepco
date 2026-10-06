module colesico.zacepco.cases.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires transitive colesico.zacepco.common.srv;
    requires transitive colesico.zacepco.script;


    exports colesico.zacepco.cases.srv.ioc;
    exports colesico.zacepco.cases.srv.model;
    exports colesico.zacepco.cases.srv.dao;
    exports colesico.zacepco.cases.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}