module colesico.zacepco.caseregistry.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires colesico.zacepco.filestorage;
    requires transitive colesico.zacepco.common.srv;
    requires transitive colesico.zacepco.script;


    exports colesico.zacepco.caseregistry.srv.ioc;
    exports colesico.zacepco.caseregistry.srv.model;
    exports colesico.zacepco.caseregistry.srv.dao;
    exports colesico.zacepco.caseregistry.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}