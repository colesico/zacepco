module colesico.zacepco.filestore {

    requires org.slf4j;
    requires colesico.framework.ioc;
    requires colesico.framework.service;
    requires colesico.framework.config;

    exports colesico.zacepco.filestore.service;
    exports colesico.zacepco.filestore.ioc;
    exports colesico.zacepco.filestore.config;
}