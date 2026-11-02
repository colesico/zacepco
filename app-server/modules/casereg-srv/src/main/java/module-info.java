module colesico.zacepco.casereg.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires colesico.zacepco.filestore;
    requires transitive colesico.zacepco.core.srv;
    requires transitive colesico.zacepco.script;
    requires transitive colesico.zacepco.investigation;

    requires com.esotericsoftware.kryo;


    exports colesico.zacepco.casereg.srv.ioc;
    exports colesico.zacepco.casereg.srv.model;
    exports colesico.zacepco.casereg.srv.dao;
    exports colesico.zacepco.casereg.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}