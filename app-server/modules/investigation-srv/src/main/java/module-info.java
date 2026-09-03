module colesico.zacepco.investigation.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires transitive colesico.zacepco.common.srv;
    requires transitive colesico.zacepco.script;


    exports colesico.zacepco.investigation.srv.ioc;
    exports colesico.zacepco.investigation.srv.model;
    exports colesico.zacepco.investigation.srv.dao;
    exports colesico.zacepco.investigation.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}