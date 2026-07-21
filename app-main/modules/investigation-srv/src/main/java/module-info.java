module colesico.zacepco.investigation.srv {

    requires jakarta.inject;
    requires colesico.framework.jdbi;
    requires colesico.framework.jdbirec;

    requires org.apache.commons.lang3;

    requires colesico.zacepco.script.lib;
    requires colesico.framework.config;

    exports colesico.zacepco.investigation.srv.ioc;
    exports colesico.zacepco.investigation.srv.model;
    exports colesico.zacepco.investigation.srv.dao;
    exports colesico.zacepco.investigation.srv.service;

}