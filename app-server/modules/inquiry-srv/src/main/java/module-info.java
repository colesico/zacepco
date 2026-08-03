module colesico.zacepco.inquiry.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires transitive colesico.zacepco.srv;
    requires transitive colesico.zacepco.script;


    exports colesico.zacepco.inquiry.srv.ioc;
    exports colesico.zacepco.inquiry.srv.model;
    exports colesico.zacepco.inquiry.srv.dao;
    exports colesico.zacepco.inquiry.srv.service;

}