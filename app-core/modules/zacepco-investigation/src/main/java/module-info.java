module colesico.zacepco.investigation {

    requires jakarta.inject;
    requires colesico.framework.ioc;
    requires colesico.framework.service;
    requires colesico.framework.jdbirec;

    requires org.apache.commons.lang3;

    requires colesico.zacepco.script;

    exports colesico.zacepco.investigation.ioc;
    exports colesico.zacepco.investigation.model;
    exports colesico.zacepco.investigation.dao;


}