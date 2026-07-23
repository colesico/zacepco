module colesico.zacepco.server {

    requires transitive colesico.framework.fusionhttp;
    requires transitive colesico.zacepco.ui;
    requires colesico.zacepco.persistence;

    requires org.slf4j;
    requires colesico.zacepco.storage;

    exports colesico.zacepco.server.ui.weblet;
    exports colesico.zacepco.server.ui.config to colesico.framework.config, colesico.framework.ioc;

    opens colesico.zacepco.server.tmpl;
    opens colesico.zacepco.server.t9n;
}