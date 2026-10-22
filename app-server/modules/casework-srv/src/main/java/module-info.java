module colesico.zacepco.casework.srv {

    requires jakarta.inject;
    requires colesico.framework.config;
    requires transitive colesico.zacepco.common.srv;
    requires transitive colesico.zacepco.scriptpack;
    requires transitive colesico.zacepco.casereg.srv;

    requires com.esotericsoftware.kryo;

    exports colesico.zacepco.casework.srv.ioc;
    exports colesico.zacepco.casework.srv.model;
    exports colesico.zacepco.casework.srv.dao;
    exports colesico.zacepco.casework.srv.service;
    // exports colesico.zacepco.inquiry.srv.dto;

}