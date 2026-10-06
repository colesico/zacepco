module colesico.zacepco.filestorage {

    requires org.slf4j;
    requires colesico.framework.ioc;
    requires colesico.framework.service;
    requires colesico.framework.config;

    exports colesico.zacepco.filestorage.service;
    exports colesico.zacepco.filestorage.ioc;
    exports colesico.zacepco.filestorage.config;
}