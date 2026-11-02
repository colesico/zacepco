module colesico.zacepco.docket.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires colesico.zacepco.filestore;
    requires transitive colesico.zacepco.core.srv;
    requires transitive colesico.zacepco.script;
    requires transitive colesico.zacepco.investigation;

    requires com.esotericsoftware.kryo;


    exports colesico.zacepco.docket.srv.ioc;
    exports colesico.zacepco.docket.srv.model;
    exports colesico.zacepco.docket.srv.dao;
    exports colesico.zacepco.docket.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}